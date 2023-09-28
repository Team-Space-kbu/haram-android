package com.space.haram_android.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.haram_android.util.ViewType
import com.space.haram_android.R
import com.space.haram_android.databinding.ActivityFunctionBinding
import com.space.haram_android.ui.book.home.BookHomeFragment
import com.space.haram_android.ui.intranet.IntranetFragment
import com.space.haram_android.util.FragmentFactory.createFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.lang.RuntimeException

@AndroidEntryPoint
class FunctionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFunctionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFunctionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_function)
        setSupportActionBar(binding.functionToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (savedInstanceState == null) {
            val viewType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("viewType", ViewType::class.java)
            } else {
                intent.getSerializableExtra("viewType") as ViewType
            }
            if (viewType!!.fragmentClass != null) {
                supportFragmentManager.commitNow {
                    replace(R.id.container, createFragment(viewType)!!)
                    setReorderingAllowed(true)
                }
            } else {
                Timber.d("잘못된 액티비티 요청으로 인한 액티비티 종료")
                finish()
            }
        }
    }
}