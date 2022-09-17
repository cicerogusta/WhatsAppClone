package com.ciceropinheiro.whatsapp_clone.di

import android.content.SharedPreferences
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepository
import com.ciceropinheiro.whatsapp_clone.data.repository.FirebaseRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
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
    fun provideAutghRepository(
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
        database: DatabaseReference,
    ): FirebaseRepository {
        return FirebaseRepositoryImp(auth, appPreferences, database)
    }
}