package com.space.partners

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.core_ui.startActivity
import com.space.partners.ui.PartnersFragment
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R

@AndroidEntryPoint
class PartnersActivity : AppCompatActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity<PartnersActivity>()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, PartnersFragment.newInstance())
            }
        }
    }
}