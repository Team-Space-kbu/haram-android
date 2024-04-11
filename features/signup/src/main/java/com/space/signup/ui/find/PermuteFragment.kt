package com.space.signup.ui.find

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.EditType
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.view.EditTextAdapter
import com.space.core_ui.binding.adapter.view.FillBottomButtonAdapter
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.shared.decodeFromString
import com.space.shared.model.EmailModel
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.core_ui.binding.adapter.view.EditTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermuteFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = PermuteFragment()
    }

    private val code by extraNotNull<String>("code")
        .map { it.decodeFromString<EmailModel>() }

    private val viewModel: PermuteViewModel by viewModels()
    private val color = Color.parseColor("#E82722")
    private val pwStatusAdapter by lazy {
        EditStatusAdapter(
            "암호 규칙이 맞지 않습니다.\n영어, 숫자, 특수문자가 포함되어야합니다.",
            viewModel.passwordStatus,
            color,
            false
        )
    }
    private val pwVerifyAdapter by lazy {
        EditStatusAdapter("비밀번호가 다릅니다.", viewModel.passwordVerifyStatus, color, false)
    }
    private val adapter by lazy {
        ConcatAdapter(
            InfoHeaderAdapter(
                "비밀번호 변경\uD83D\uDD11",
                "비밀번호를 재설정하기 위해\n새로 변경할 비밀번호를 입력해주세요\n"
            ),
            EditTitleAdapter("비밀번호"),
            EditTextAdapter(viewModel.password, "비밀번호", EditType.PASSWORD),
            pwStatusAdapter,
            EditTitleAdapter("비밀번호 확인"),
            EditTextAdapter(viewModel.passwordVerify, "비밀번호 확인", EditType.PASSWORD),
            pwVerifyAdapter
        )
    }

    override fun init() {
        code.let {
            viewModel.emailModel = it
        }
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
        viewModel.uiStatus.observe(this) {
            if (it) {
                activity?.finish()
            }
        }
        viewModel.passwordStatus.observe(this) {
            pwStatusAdapter.setVisibility(it)
        }
        viewModel.passwordVerifyStatus.observe(this) {
            pwVerifyAdapter.setVisibility(it)
        }
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter =
            FillBottomButtonAdapter("변경하기", false, adapter) {
                viewModel.setNewPw()
            }
    }
}