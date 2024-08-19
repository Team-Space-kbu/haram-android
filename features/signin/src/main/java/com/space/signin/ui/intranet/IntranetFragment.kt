package com.space.signin.ui.intranet

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.signin.R
import com.space.core_ui.extension.logEvent
import com.space.shared.UiStatusType
import com.space.shared.model.IntranetModel
import com.space.signin.databinding.FragmentIntranetContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntranetFragment :
    BaseFragment<FragmentIntranetContainerBinding>(R.layout.fragment_intranet_container) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
        firebaseAnalytics.logEvent("login") {
            param("screen_view", "intranet")
        }
    }

    override fun initListener() {
        binding.intLoginButton.setOnClickListener {
            val user = binding.username.text.toString()
            val pw = binding.password.text.toString()
            viewModel.setIntranet(IntranetModel(user, pw))
        }
        binding.intBackHome.setOnClickListener {
            activity?.finish()
        }
    }

    override fun afterObserverListener() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                UiStatusType.REJECT -> {
                    binding.loginFail.visibility = View.VISIBLE
                }

                UiStatusType.SUCCESS -> {
                    activity?.finish()
                    Toast.makeText(context, "로그인을 성공했습니다.", Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(context, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}