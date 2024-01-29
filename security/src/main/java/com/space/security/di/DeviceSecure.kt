package com.space.security.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AndroidDeviceSecure {

    @Provides
    @Singleton
    @SuppressLint("HardwareIds")
    fun bindDeviceSecure(
        @ApplicationContext context: Context,
    ): DeviceSecure {
        val ssid = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return DeviceSecure(ssid)
    }
}

data class DeviceSecure(
    val ssid: String
)