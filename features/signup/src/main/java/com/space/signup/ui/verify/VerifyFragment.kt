package com.space.signup.ui.verify

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.signup.ui.binding.adapter.EditTitleAdapter
import com.space.signup.ui.binding.adapter.InfoHeaderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = VerifyFragment()
    }

    private val viewModel: VerifyViewModel by viewModels()
    private val adapter = ConcatAdapter(
        InfoHeaderAdapter(
            "회원가입✏\uFE0F",
            "사용하실 계정 정보를 작성해주세요\n입력된 정보를 암호화 처리되어 사용자만 볼 수 있습니다."
        ),
        EditTitleAdapter("학교 이메일"),
    )

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
    }

}