package com.space.haram_android.ui.login


import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentLoginBinding
import com.space.haram_android.data.response.LoginToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

//    private val viewModel: LoginViewModel by viewModels()

    override fun init() {
        super.init()
        this.viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun initListener() {
        super.initListener()

        this.viewModel.getUsers().observe(this@LoginFragment, Observer<LoginToken?> {
            it?.let { it1 ->
                Log.i("TEST", it1.accessToken)
                Log.i("TEST", it.refreshToken)
            }

        })
    }

}