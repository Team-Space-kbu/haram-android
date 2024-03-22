package com.space.image

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.startActivity
import com.space.core_ui.R
import com.space.core_ui.startFragment
import com.space.image.ui.ImageFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {

    private val image by extraNotNull<String>("image").map { it }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<ImageFragment>(
                R.id.container,
                "image" to image
            )
        }
    }

    companion object {
        fun open(context: Context, image: String) {
            context.startActivity<ImageActivity>(
                "image" to image
            )
        }
    }
}