package com.space.auth.ui

import androidx.fragment.app.viewModels
import com.space.auth.R
import com.space.auth.databinding.FragmentAuthContainerBinding
import com.space.core_ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentAuthContainerBinding>(R.layout.fragment_auth_container) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()


}