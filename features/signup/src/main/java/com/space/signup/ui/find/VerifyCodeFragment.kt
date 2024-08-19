package com.space.signup.ui.find

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.extension.EditType
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.view.EditTextAdapter
import com.space.core_ui.binding.adapter.view.Fill2wayButtonAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.UiStatusType
import com.space.shared.encodeToString
import com.space.core_ui.binding.adapter.view.EditTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyCodeFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = VerifyCodeFragment()
    }

    private val email by extraNotNull<String>("email")
        .map { encodeString -> encodeString }

    private val viewModel: VerifyCodeViewModel by viewModels()

    private val adapter by lazy {
        ConcatAdapter(
            InfoHeaderAdapter(
                "비밀번호 찾기\uD83D\uDCE9",
                "입력하신 이메일로 인증 코드를 발송했습니다.\n이메일을 확인해주세요\uD83D\uDCEC"
            ),
            EditTitleAdapter("이메일 확인코드"),
            EditTextAdapter(viewModel.emailCode, "확인코드", EditType.NUMBER)
        )
    }

    override fun beforeObserverListener() {
        viewModel.uiStatus.observe(this) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                parentFragmentManager.transformFragment<PermuteFragment>(
                    R.id.container,
                    "code" to it.data!!.encodeToString()
                )
            }
        }
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
    }

    override fun init() {
        viewModel.email = email
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter =
            Fill2wayButtonAdapter(adapter, { parentFragmentManager.popBackStack() }) {
                viewModel.findPw()
            }
    }


}