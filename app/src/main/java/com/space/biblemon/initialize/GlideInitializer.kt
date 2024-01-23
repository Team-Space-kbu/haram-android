package com.space.biblemon.initialize

import android.content.Context
import androidx.startup.Initializer
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory


class GlideInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.LOW)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}