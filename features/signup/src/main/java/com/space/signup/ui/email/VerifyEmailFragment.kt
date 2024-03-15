package com.space.signup.ui.email

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.FillBottomButtonAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.transformFragment
import com.space.signup.ui.email.adapter.EditEmailAdapter
import com.space.signup.ui.binding.adapter.EditTitleAdapter
import com.space.signup.ui.binding.adapter.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditVerifyEmailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyEmailFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = VerifyEmailFragment()
    }

    private val adapter by lazy {
        ConcatAdapter(
            InfoHeaderAdapter(
                "이메일 인증 \uD83D\uDCE8",
                "서비스를 이용하기 전 학생인지 확인 절차입니다\n" + "비밀번호를 찾거나 정보를 찾을 때 사용됩니다."
            ),
            EditTitleAdapter("학교 이메일"),
            EditEmailAdapter(viewModel.email),
            EditTitleAdapter("이메일 확인"),
            EditVerifyEmailAdapter(viewModel.emailVerify) {

            },
        )
    }
    private val viewModel: VerifyEmailViewModel by viewModels()

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter =
            FillBottomButtonAdapter("예약하기", false, adapter) {

            }
    }


}