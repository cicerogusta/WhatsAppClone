package com.ciceropinheiro.whatsapp_clone.di

import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        auth: FirebaseAuth,
        database: FirebaseDatabase,
        storage: FirebaseStorage
    ): FirebaseRepository {
        return FirebaseRepositoryImp(auth, database, storage)
    }
}