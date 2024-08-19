package com.space.signup.ui.email

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.UiStatusType
import com.space.shared.encodeToString
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.signup.ui.email.adapter.EditEmailAdapter
import com.space.core_ui.binding.adapter.view.EditTitleAdapter
import com.space.core_ui.binding.adapter.view.Fill2wayButtonAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.signup.ui.find.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditVerifyEmailAdapter
import com.space.signup.ui.signup.SignupVerifyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyEmailFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = VerifyEmailFragment()
    }

    private val policy by extraNotNull<String>("policy").map { it }

    private val viewModel: VerifyEmailViewModel by viewModels()
    private val editAdapter by lazy {
        EditStatusAdapter(
            "이메일이 성공적으로 발송되었습니다.",
            viewModel.verifyStatus,
            requireContext().getColor(R.color.spaceBlue)
        )
    }
    private val adapter by lazy {
        ConcatAdapter(
            InfoHeaderAdapter(
                "이메일 인증 \uD83D\uDCE8",
                "서비스를 이용하기 전 학생인지 확인 절차입니다\n비밀번호를 찾거나 정보를 찾을 때 사용됩니다."
            ),
            EditTitleAdapter("학교 이메일"),
            EditEmailAdapter(viewModel.email),
            EditTitleAdapter("이메일 확인"),
            EditVerifyEmailAdapter(viewModel.emailVerify) {
                viewModel.sendEmail()
            },
            editAdapter
        )
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.adapter =
            Fill2wayButtonAdapter(adapter, { activity?.finish() }) {
                viewModel.verifyCode()
            }
    }

    override fun beforeObserverListener() {
        viewModel.uiStatus.observe(this) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                parentFragmentManager.transformFragment<SignupVerifyFragment>(
                    R.id.container,
                    "email" to it.data.encodeToString(),
                    "policy" to policy
                )
            }
        }
        viewModel.verifyModel.observe(this) {
            editAdapter.setStatus(it.first, Color.parseColor(it.second))
        }
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }

    }


}