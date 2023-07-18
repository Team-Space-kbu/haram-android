package com.space.haram_android.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.space.data.type.ViewType
import com.space.data.type.ViewType.*
import com.space.haram_android.R
import com.space.haram_android.databinding.ActivityFunctionBinding
import com.space.haram_android.ui.book.home.BookHomeFragment
import com.space.haram_android.ui.intranet.IntranetFragment
import dagger.hilt.android.AndroidEntryPoint
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

            try {
                when (viewType) {
                    INTRANET_ -> TODO()

                    INTRANET_CHAPEL -> {
                        supportFragmentManager.commitNow {
                            replace(R.id.container, IntranetFragment.newInstance())
                            setReorderingAllowed(true)
                        }
                    }

                    BOOK_HOME -> {
                        supportFragmentManager.commitNow {
                            replace(R.id.container, BookHomeFragment.newInstance())
                            setReorderingAllowed(true)
                        }
                    }

                    else -> {
                        throw RuntimeException("잘못된 값이 요청되었습니다.")
                    }
                }
            } catch (e: Exception) {
                Log.d("FunctionActivity", "잘못된 액티비티 요청으로 인한 액티비티 종료\nMessage : ${e.message}")
                finish()
            }


        }
    }
}