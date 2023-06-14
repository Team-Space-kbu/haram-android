package com.space.haram_android.ui.intranet


import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.databinding.FragmentIntranetBinding
import com.space.haram_android.ui.chapel.ChapelFragment
import com.space.haram_android.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntranetFragment : BaseFragment<FragmentIntranetBinding>(R.layout.fragment_intranet) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "인트라넷"
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginStatus.observe(viewLifecycleOwner, Observer {
            binding.loginFail.visibility = if (it) View.GONE else View.VISIBLE
            if (it) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ChapelFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
        })
        loginDataStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ChapelFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    override fun initListener() = with(binding) {
        super.initListener()
        loginBackground.setOnClickListener {
            keyboardDown(loginBackground)
        }
        intLoginButton.setOnClickListener() {
            login()
        }
        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            return@setOnEditorActionListener false
        }
        password.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                login()
                return@OnKeyListener true
            }
            return@OnKeyListener false
        })
        intBackHome.setOnClickListener {
            activity?.finish()
        }
    }

    private fun login() = with(binding) {
        keyboardDown(password)
        viewModel.login(
            LoginIntranetModel(null, username.text.toString(), password.text.toString())
        )
    }

    private fun keyboardDown(view: View) {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}