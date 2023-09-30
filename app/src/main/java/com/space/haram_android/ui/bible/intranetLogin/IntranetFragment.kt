package com.space.haram_android.ui.bible.intranetLogin


import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.space.haram_android.BR
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentIntranetBinding
import com.space.haram_android.util.FragmentFactory.createFragment
import com.space.haram_android.util.ViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntranetFragment : BaseFragment<FragmentIntranetBinding>(R.layout.fragment_intranet) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()
    override fun init() {
        super.init()
        this.toolbarTitle = "인트라넷"
    }

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        binding.intBackHome.setOnClickListener {
            activity?.finish()
        }
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        loginForm.observe(viewLifecycleOwner) {
            if (it.loginDataStatus || it.loginStatus) {
                newFragmentView(createFragment(ViewType.INTRANET_CHAPEL)!!)
            }
        }
        viewListener.observe(viewLifecycleOwner) {
            if (it.keyEvent) {
                makeLoginModel(binding.username.text, binding.password.text).run {
                    viewModel.intranetLogin(this)
                }
                bindingKeyListener.keyEventEnd()
            }
        }
    }
}