package com.space.signup.ui.find

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter

import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.Fill2wayButtonAdapter
import com.space.core_ui.showToast
import com.space.core_ui.transformFragment
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.core_ui.binding.adapter.EditTitleAdapter
import com.space.signup.ui.binding.adapter.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditEmailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindPwEmailFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = FindPwEmailFragment()
    }

    private val viewModel: FindPwEmailViewModel by viewModels()
    private val adapter by lazy {
        ConcatAdapter(
            InfoHeaderAdapter(
                "비밀번호 찾기\uD83D\uDD10",
                "비밀번호를 재설정하기 위해 코드를 인증해야합니다.\n사용자 이메일을 입력해주세요"
            ),
            EditTitleAdapter("학교 이메일"),
            EditEmailAdapter(viewModel.email),
            EditStatusAdapter(
                "이메일 형식이 맞지 않습니다.\n@bible.ac.kr 이메일로 입력해주세요",
                viewModel.verifyStatus,
                Color.parseColor("#E82722")
            )
        )
    }


    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = Fill2wayButtonAdapter(adapter, { activity?.finish() }) {
            viewModel.sendEmail()
        }
    }

    override fun beforeObserverListener() {
        viewModel.uiStatus.observe(this) {
            if (it) {
                parentFragmentManager.transformFragment<VerifyCodeFragment>(
                    R.id.container,
                    "email" to viewModel.email.value.toString()
                )
            }
        }
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
    }

}