package com.space.signin.ui.login


import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.google.firebase.analytics.FirebaseAnalytics
import com.space.core_ui.base.BaseFragment
import com.space.signin.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.hideKeyboard
import com.space.core_ui.logEvent
import com.space.shared.type.SingupType
import com.space.signin.BR
import com.space.signin.databinding.FragmentLoginBinding
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
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        binding.password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                requireContext().hideKeyboard(binding.password)
                viewModel.onClick
                return@setOnEditorActionListener true
            }
            false
        }
        binding.password.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                requireContext().hideKeyboard(binding.password)
                viewModel.onClick
                return@setOnKeyListener true
            }
            false
        }
        binding.singupButton.setOnClickListener {
            viewModel.navigatorSingup.openView(requireContext())
        }
        binding.findPwText.setOnClickListener {
            viewModel.navigatorSingup.openView(requireContext(), SingupType.FIND_PW)
        }
    }

    override fun afterObserverListener() = with(viewModel) {
        loginState.observe(viewLifecycleOwner) {
            if (it.equals(LoginStatus.Success)) {
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
                    param(FirebaseAnalytics.Event.LOGIN, viewModel.username.value.toString())
                }
                activity?.finish()
                navigatorMain.openView(requireContext())
            }
        }
    }


}