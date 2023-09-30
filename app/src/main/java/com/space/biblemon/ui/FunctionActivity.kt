package com.space.biblemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.biblemon.util.ViewType
import com.space.biblemon.R
import com.space.biblemon.databinding.ActivityFunctionBinding
import com.space.biblemon.util.FragmentFactory.createFragment
import dagger.hilt.android.AndroidEntryPoint
import extension.ExtensionFunction.intentSerializable
import timber.log.Timber

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
            val viewType = intent.intentSerializable("viewType", ViewType::class.java)
            if (createFragment(viewType!!) != null) {
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