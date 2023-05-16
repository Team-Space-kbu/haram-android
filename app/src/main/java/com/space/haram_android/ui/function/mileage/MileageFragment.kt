package com.space.haram_android.ui.function.mileage

import androidx.lifecycle.ViewModelProvider
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentMileageBinding
import com.space.haram_android.ui.base.BaseFragment

class MileageFragment : BaseFragment<FragmentMileageBinding, MileageViewModel>(R.layout.fragment_mileage) {

    companion object {
        fun newInstance() = MileageFragment()
    }

    override fun init() {
        super.init()
        this.viewModel = ViewModelProvider(this)[MileageViewModel::class.java]
    }

}