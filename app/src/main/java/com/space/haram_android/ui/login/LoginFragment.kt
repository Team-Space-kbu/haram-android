package com.space.haram_android.ui.login


import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.common.data.model.login.LoginModel
import com.space.haram_android.databinding.FragmentLoginBinding
import com.space.haram_android.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()


    override fun afterViewCreated() = with(binding) {
        super.afterViewCreated()
        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            if (it.isDataValid) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
            loginFail.visibility = if (it.statusLogin) View.GONE else View.VISIBLE
        })
    }

    override fun initListener() = with(binding) {
        super.initListener()
        loginBackground.setOnClickListener {
            keyboardDown(loginBackground)
        }
        loginButton.setOnClickListener() {
            login()
        }
        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }
        password.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                login()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun login() = with(binding) {
        keyboardDown(password)
        viewModel.login(
            LoginModel(username.text.toString(), password.text.toString())
        )
    }

    private fun keyboardDown(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}