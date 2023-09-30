package com.space.biblemon.ui.auth.find

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentFindEmailBinding

class FindEmailFragment : BaseFragment<FragmentFindEmailBinding>(R.layout.fragment_find_email) {

    companion object {
        fun newInstance() = FindEmailFragment()
    }

    private val viewModel: FindEmailViewModel by viewModels()


}