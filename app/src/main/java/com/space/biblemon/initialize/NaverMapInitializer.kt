package com.space.biblemon.initialize

import android.content.Context
import androidx.startup.Initializer
import com.naver.maps.map.NaverMapSdk
import com.space.biblemon.BuildConfig

class NaverMapInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        NaverMapSdk.getInstance(context).client = NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NCP_CLIENT_ID_API_KEY)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}