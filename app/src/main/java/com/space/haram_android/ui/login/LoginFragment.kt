package com.space.haram_android.ui.login


import android.content.Intent
import androidx.fragment.app.viewModels
import com.space.haram_android.R.layout.fragment_login
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentLoginBinding
import com.space.haram_android.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(fragment_login) {

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
        loginFormState.observe(viewLifecycleOwner) {
            if (it.statusLogin || it.isTokenValid) {
                startActivity(Intent(context, HomeActivity::class.java))
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