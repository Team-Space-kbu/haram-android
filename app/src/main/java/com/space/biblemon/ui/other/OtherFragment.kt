package com.space.biblemon.ui.other

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BaseFragment<FragmentOtherBinding>(R.layout.fragment_other) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    private val viewModel: OtherViewModel by viewModels()


}