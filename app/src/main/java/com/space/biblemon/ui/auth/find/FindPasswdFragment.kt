package com.space.biblemon.ui.auth.find

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentFindPasswdBinding

class FindPasswdFragment : BaseFragment<FragmentFindPasswdBinding>(R.layout.fragment_find_passwd) {

    companion object {
        fun newInstance() = FindPasswdFragment()
    }

    private val viewModel: FindPasswdViewModel by viewModels()


}