package com.space.biblemon.ui.auth.find

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentFindVerifyBinding

class FindVerifyFragment : BaseFragment<FragmentFindVerifyBinding>(R.layout.fragment_find_verify) {

    companion object {
        fun newInstance() = FindVerifyFragment()
    }

    private val viewModel: FindVerifyViewModel by viewModels()


}