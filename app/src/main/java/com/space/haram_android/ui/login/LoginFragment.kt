package com.space.haram_android.ui.login


import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.data.model.LoginModel
import com.space.haram_android.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()


    override fun afterViewCreated() = with(binding) {
        super.afterViewCreated()
        viewModel.userModel.observe(viewLifecycleOwner, Observer {
            loginFail.visibility = if (it == null) View.VISIBLE else View.GONE
        })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                viewModel.loginDataChanged(
                    username.text.toString(), password.text.toString()
                )
            }
        }
        username.addTextChangedListener(afterTextChangedListener)
        password.addTextChangedListener(afterTextChangedListener)
        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.KEYCODE_ENTER) {
                keyboardDown(password)
                viewModel.login(
                    LoginModel(username.text.toString(), password.text.toString(),)
                )
            }
            false
        }
    }

    override fun initListener() = with(binding) {
        super.initListener()
        loginBackground.setOnClickListener {
            keyboardDown(loginBackground)
        }
        loginButton.setOnClickListener() {
            keyboardDown(loginButton)
            viewModel.login(
                LoginModel(username.text.toString(), password.text.toString(),)
            )
        }
    }


    private fun keyboardDown(view: View) {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}