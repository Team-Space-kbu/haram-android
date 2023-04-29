package com.space.haram_android.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentMainBinding
import com.space.haram_android.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun init() {
        super.init()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }


}