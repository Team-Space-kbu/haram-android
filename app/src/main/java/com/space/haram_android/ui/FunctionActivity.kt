package com.space.haram_android.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.space.haram_android.R
import com.space.haram_android.common.data.ViewType.*
import com.space.haram_android.databinding.ActivityFunctionBinding
import com.space.haram_android.ui.book.home.BookHomeFragment
import com.space.haram_android.ui.intranet.IntranetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FunctionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFunctionBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFunctionBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_function)
        setSupportActionBar(binding.functionToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            val viewType = intent.getSerializableExtra("viewType")
            try {
                when (viewType) {
                    INTRANET_ -> TODO()

                    INTRANET_CHAPEL ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, IntranetFragment.newInstance())
                            .commitNow()
                    }

                    BOOK_HOME ->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, BookHomeFragment.newInstance())
                            .commitNow()
                    }

                }
            } catch (e: Exception) {
                Log.d("FunctionActivity", "잘못된 액티비티 요청으로 인한 액티비티 종료\nMessage : ${e.message}")
                finish()
            }


        }
    }
}