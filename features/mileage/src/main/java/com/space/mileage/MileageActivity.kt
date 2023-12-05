package com.space.mileage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.core_ui.startActivity
import com.space.mileage.ui.MileageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MileageActivity : AppCompatActivity() {
    companion object {
        fun open(context: Context) {
            context.startActivity<MileageActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mileage)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, MileageFragment.newInstance())
                setReorderingAllowed(true)
            }
        }
    }
}