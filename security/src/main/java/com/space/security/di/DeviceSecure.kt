package com.space.security.di

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import com.space.shared.model.Device
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
        val deviceManufacturer = Build.BRAND
        val deviceModel = Build.MODEL;
        val deviceRelease = Build.VERSION.RELEASE;
        val deviceType = "ANDROID"
        return DeviceSecure(
            ssid,
            Device(deviceManufacturer, deviceModel, deviceType, deviceRelease)
        )
    }
}

data class DeviceSecure(
    val ssid: String,
    val device: Device
)