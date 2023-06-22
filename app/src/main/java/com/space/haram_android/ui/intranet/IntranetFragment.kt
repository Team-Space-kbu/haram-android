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
        binding.viewModel = viewModel
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginStatus.observe(viewLifecycleOwner, Observer {
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
        intLoginButton.setOnClickListener() {
            login()
        }
        intBackHome.setOnClickListener {
            activity?.finish()
        }
    }

    private fun login() {
        viewModel.login(
            LoginIntranetModel(
                null,
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        )
    }
}