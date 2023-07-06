package com.space.haram_android.ui.intranet


import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.space.data.model.LoginIntranetModel
import com.space.haram_android.BR
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentIntranetBinding
import com.space.haram_android.ui.chapel.ChapelFragment
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
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginForm.observe(viewLifecycleOwner) {
            if (it.loginDataStatus || it.loginStatus) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ChapelFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit()
            }
        }
        loginBackEvent.observe(viewLifecycleOwner) {
            if (it) {
                activity?.finish()
            }
        }
        loginEvent.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.intranetLogin(
                    LoginIntranetModel(
                        null,
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    )
                )
                bindingListener.keyEventEnd()
            }
        }
    }
}