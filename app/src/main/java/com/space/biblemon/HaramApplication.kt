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


        // 옵션 설정 (옵션을 필요에 따라 조정할 수 있음)
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW)
    }
}