package com.space.biblemon.ui.auth.singUp

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentSingupBinding

class SingUprFragment : BaseFragment<FragmentSingupBinding>(R.layout.fragment_singup) {

    companion object {
        fun newInstance() = SingUprFragment()
    }

    private val viewModel: SingUprFragmentViewModel by viewModels()


}