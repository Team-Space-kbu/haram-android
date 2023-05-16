package com.space.haram_android.ui.other

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentOtherBinding
import com.space.haram_android.ui.base.BaseFragment

class OtherFragment : BaseFragment<FragmentOtherBinding, OtherViewModel>(R.layout.fragment_other) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    override fun init() {
        super.init()
        this.viewModel = ViewModelProvider(this).get(OtherViewModel::class.java)
    }

}