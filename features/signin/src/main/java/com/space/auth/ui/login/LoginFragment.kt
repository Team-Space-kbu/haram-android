package com.space.auth.ui.login


import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.space.auth.BR
import com.space.auth.R
import com.space.auth.databinding.FragmentLoginBinding
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.hideKeyboard
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
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                requireContext().hideKeyboard(binding.password)
                viewModel.onClick
            }
            false
        }
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginState.observe(viewLifecycleOwner) {
            if (it.equals(LoginStatus.Success)) {
                navigatorMain.openView(requireContext())
                activity?.finish()
            }
        }
    }


}