package com.space.auth.ui.login


import androidx.fragment.app.viewModels
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginState.observe(viewLifecycleOwner) {
            if (it) {
                navigatorMain.openMain(requireContext())
                activity?.finish()
            }
        }

//        makeLoginModel(binding.username.text, binding.password.text)
    }

}