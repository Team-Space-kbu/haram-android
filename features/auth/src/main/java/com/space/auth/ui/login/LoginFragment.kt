package com.space.auth.ui.login


import androidx.fragment.app.viewModels
import com.space.auth.BR
import com.space.auth.R
import com.space.auth.databinding.FragmentLoginBinding
import com.space.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()


    override fun initView() {
        super.initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.onClick, viewModel.onClick)
        binding.lifecycleOwner = this
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginState.observe(viewLifecycleOwner) {
            if (it) {
                navigatorMain.openMain(requireContext())
                activity?.finish()
            }
        }
    }

}