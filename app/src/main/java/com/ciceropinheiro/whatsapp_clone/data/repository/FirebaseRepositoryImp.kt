package com.ciceropinheiro.whatsapp_clone.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.util.UiState
import com.ciceropinheiro.whatsapp_clone.util.codificarBase64
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream


class FirebaseRepositoryImp(
    val auth: FirebaseAuth,
    val database: FirebaseDatabase,
    val storage: FirebaseStorage
) : FirebaseRepository {


    override fun loginUser(
        email: String,
        senha: String,
        result: (UiState<String>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Logado com Sucesso"))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Falha ao logar, verifique email e senha"))
            }
    }

    override fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Email has been sent"))

                } else {
                    result.invoke(UiState.Failure(task.exception?.message))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email"))
            }
    }

    override fun logout() {
        auth.signOut()
    }


    override fun registerUser(user: User, result: (UiState<String>) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.senha).addOnCompleteListener {
            if (it.isSuccessful) {
                database.reference.child("usuarios").setValue(user)
                result.invoke(UiState.Success("Registrado com Sucesso"))
            }

        }.addOnFailureListener {
            result.invoke(UiState.Failure(it.toString()))
        }


    }

    override fun getUserProfileInDatabase(liveData: MutableLiveData<User>) {
        val uid = auth.currentUser?.uid.toString()
        database.reference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveData.postValue(snapshot.getValue(User::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun getAllUsers(
        liveDataAllUsers: MutableLiveData<MutableList<User>>,
        liveDataProfile: MutableLiveData<User>
    ) {
        database.reference.addValueEventListener(object : ValueEventListener {
            val listUsuarios = mutableListOf<User>()


            override fun onDataChange(snapshot: DataSnapshot) {

                for (users in snapshot.children) {
                    users.getValue(User::class.java)?.let { listUsuarios.add(it) }

                }
                liveDataAllUsers.value = listUsuarios

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun isCurrentUser(): Boolean {
        var isCurrentUser = false
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            isCurrentUser = true

        }
        return isCurrentUser
    }

    override fun saveUserImageGalery(imagem: Uri, context: Context) {
        val storageReference =
            storage.reference.child("imagens")
                .child("perfil")
                .child(getUserId()!! + ".jpeg")
//                .child("$it.jpeg")


        val uploadTask = storageReference.putFile(imagem)
        uploadTask.addOnFailureListener {
            Toast.makeText(context, "ERRO: $it", Toast.LENGTH_SHORT).show()

        }.addOnSuccessListener {
            storageReference.downloadUrl.addOnCompleteListener {
                updateProfile(it.result)
                Toast.makeText(context, "SUCESSO: ${it.result}", Toast.LENGTH_SHORT).show()



            }
        }

    }

    override fun saveUserImageCamera(imagem: Bitmap, context: Context) {

        //Recuperar dados da imagem para o firebase
        val baos = ByteArrayOutputStream()
        imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos)
        val dadosImagem = baos.toByteArray()
        val storageReference =
            storage.reference.child("imagens")
                .child("perfil")
                .child(getUserId()!! + ".jpeg")
//                .child("$it.jpeg")


        val uploadTask = storageReference.putBytes(dadosImagem)
        uploadTask.addOnFailureListener {
            Toast.makeText(context, "ERRO: $it", Toast.LENGTH_SHORT).show()

        }.addOnSuccessListener {
            storageReference.downloadUrl.addOnCompleteListener {
                updateProfile(it.result)
                Toast.makeText(context, "SUCESSO: ${it.result}", Toast.LENGTH_SHORT).show()



            }
        }

    }

    override fun getUserId(): String? {
        return auth.currentUser?.email?.let { codificarBase64(it) }
    }

    override fun getUserProfilePhoto(context: Context): Uri? {

        storage.reference.child("imagens").child("perfil").child(getUserId()!! + ".jpeg").downloadUrl.addOnSuccessListener {
            updateProfile(it)
            Toast.makeText(context, "SUCESSO", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {
            Toast.makeText(context, "ERRO :$it", Toast.LENGTH_SHORT).show()
        }
        return auth.currentUser?.photoUrl

    }

    override fun updateProfile(url: Uri): Boolean {

        try {
            val user = auth.currentUser
            val profile = UserProfileChangeRequest.Builder().setPhotoUri(url).build()
            user?.updateProfile(profile)?.addOnCompleteListener {
                if(!it.isSuccessful){
                    Log.d("Perfil", "Erro ao atualizar foto de perfil")

                }

            }

            return true

        }catch (e: Exception) {
            e.printStackTrace()
            return false

        }
    }


}