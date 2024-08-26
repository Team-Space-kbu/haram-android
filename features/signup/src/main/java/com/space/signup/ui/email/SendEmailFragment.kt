package com.space.signup.ui.email

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.UiStatusType
import com.space.shared.encodeToString
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.signup.ui.email.adapter.EditEmailAdapter
import com.space.core_ui.binding.adapter.item.input.EditTitleAdapter
import com.space.core_ui.binding.adapter.view.Fill2wayButtonAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.signup.ui.find.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditVerifyEmailAdapter
import com.space.signup.ui.signup.SignupVerifyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendEmailFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = SendEmailFragment()
    }

    private val policy by extraNotNull<String>("policy").map { it }

    private val viewModel: SendEmailViewModel by viewModels()
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
            editAdapter
        )
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter =
            Fill2wayButtonAdapter(adapter, { parentFragmentManager.popBackStack() }) {
                viewModel.sendEmail()
            }
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_none)
            )
        )
    }

    override fun beforeObserverListener() {
        viewModel.sendStatus.observe(this){
            if(it){
                parentFragmentManager.transformFragment<VerifyEmailFragment>(
                    R.id.container,
                    "policy" to policy,
                    "email" to viewModel.email.value.toString(),
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