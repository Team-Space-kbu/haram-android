package com.space.haram_android.di.encrypted

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthSharedPreferencesModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthEncrypted

    @Provides
    @Singleton
    @AuthEncrypted
    fun provideAuthEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey,
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    companion object {
        private const val FILE_NAME = "space_encrypted_login"
    }

}