package com.space.haram_android.ui.auth.singUp

import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentPostBinding

class SingUprFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_singup) {

    companion object {
        fun newInstance() = SingUprFragment()
    }

    private val viewModel: SingUprFragmentViewModel by viewModels()



}