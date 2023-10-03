package com.space.biblemon

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class HaramApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NCP_CLIENT_ID_API_KEY)

        Glide.get(this).setMemoryCategory(MemoryCategory.LOW)
    }
}