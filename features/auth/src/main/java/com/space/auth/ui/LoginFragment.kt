package com.space.auth.ui


import androidx.fragment.app.viewModels
import com.space.auth.R
import com.space.auth.databinding.FragmentLoginBinding
import com.space.core_ui.base.BaseFragment
import com.space.navigator.NavigatorMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigatorMain: NavigatorMain

    override fun initView() {
        super.initView()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginFormState.observe(viewLifecycleOwner) {
            if (it.statusLogin || it.isTokenValid) {
                navigatorMain.openMain(requireContext())
                activity?.finish()
            }
        }
        loginEvent.observe(viewLifecycleOwner) {
            if (it){
                makeLoginModel(binding.username.text, binding.password.text).run {
                    biblemonLogin(this)
                }
                bindingListener.keyEventEnd()
            }
        }
    }

}