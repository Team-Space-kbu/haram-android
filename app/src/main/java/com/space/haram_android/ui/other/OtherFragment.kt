package com.space.haram_android.ui.other

import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BaseFragment<FragmentOtherBinding>(R.layout.fragment_other) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    private val viewModel: OtherViewModel by viewModels()


}