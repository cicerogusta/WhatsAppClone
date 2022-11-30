package com.ciceropinheiro.whatsapp_clone.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.whatsapp_clone.data.model.User
import com.ciceropinheiro.whatsapp_clone.util.UiState
import com.ciceropinheiro.whatsapp_clone.util.codificarBase64
import com.ciceropinheiro.whatsapp_clone.util.retornaIdUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayInputStream
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
        auth.signInWithEmailAndPassword(email,senha)
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
        if (firebaseUser !=null) {
            isCurrentUser = true

        }
        return isCurrentUser
    }

    override fun saveUserImage(imagem: Bitmap, context: Context) {
        val baos = ByteArrayOutputStream()
        val dadosImagem = baos.toByteArray()
        imagem.compress(Bitmap.CompressFormat.JPEG, 70 ,baos )
        val storageReference = storage.reference.child("imagens")
            .child("perfil")
            .child(auth.currentUser?.email?.retornaIdUsuario(auth).toString())
            .child("perfil.jpeg")

        val uploadTask = storageReference.putBytes(dadosImagem)

        uploadTask.addOnFailureListener {
            Log.e("erro", it.toString())

        }.addOnSuccessListener {
            Toast.makeText(context, "Sucesso ao colocar imagem", Toast.LENGTH_SHORT).show()


        }
    }


}