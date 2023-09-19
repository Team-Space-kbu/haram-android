package com.space.haram_android.ui.singUp

import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentPostBinding

class SingUprFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_singup_fragment) {

    companion object {
        fun newInstance() = SingUprFragment()
    }

    private val viewModel: SinguprFagmentViewModel by viewModels()



}