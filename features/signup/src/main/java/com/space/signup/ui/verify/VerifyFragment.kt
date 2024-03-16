package com.space.signup.ui.verify

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.EditType
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.EditTextAdapter
import com.space.core_ui.binding.adapter.FillBottomButtonAdapter
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.shared.decodeFromString
import com.space.shared.model.EmailModel
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.signup.ui.binding.adapter.EditTitleAdapter
import com.space.signup.ui.binding.adapter.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditEmailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = VerifyFragment()
    }

    private val email by extraNotNull<String>("email")
        .map { encodeString ->
            encodeString.decodeFromString<EmailModel>()
        }
    private val color = Color.parseColor("#E82722")
    private val viewModel: VerifyViewModel by viewModels()


    private val idStatusAdapter by lazy {
        EditStatusAdapter("아이디가 이미 존재합니다.", viewModel.userStatus, color, false)
    }
    private val nicknameStatusAdapter by lazy {
        EditStatusAdapter("nickname 은 한글 숫자 영어만 가능합니다.", viewModel.nickNameStatus, color, false)
    }
    private val pwStatusAdapter by lazy {
        EditStatusAdapter("암호 규칙이 맞지 않습니다.\n영어, 숫자, 특수문자가 포함되어야합니다.", viewModel.passwordStatus, color, false)
    }
    private val pwVerifyAdapter by lazy {
        EditStatusAdapter("비밀번호가 다릅니다.", viewModel.passwordVerifyStatus, color, false)
    }
    private val emailStatusAdapter by lazy {
        EditStatusAdapter("이메일 정보가 이미 등록되어 있습니다.", viewModel.emailStatus, color, false)
    }


    override fun init() {
        viewModel.email.value = email.email
    }

    override fun initView() {
        val adapter = ConcatAdapter(
            InfoHeaderAdapter(
                "회원가입✏\uFE0F",
                "사용하실 계정 정보를 작성해주세요\n입력된 정보를 암호화 처리되어 사용자만 볼 수 있습니다."
            ),
            EditTitleAdapter("아이디"),
            EditTextAdapter(viewModel.userId, "아이디", EditType.ID),
            idStatusAdapter,
            EditTitleAdapter("닉네임"),
            EditTextAdapter(viewModel.nickName, "닉네임", EditType.TEXT),
            nicknameStatusAdapter,
            EditTitleAdapter("비밀번호"),
            EditTextAdapter(viewModel.password, "비밀번호", EditType.PASSWORD),
            pwStatusAdapter,
            EditTitleAdapter("비밀번호 확인"),
            EditTextAdapter(viewModel.passwordVerify, "비밀번호 확인", EditType.PASSWORD),
            pwVerifyAdapter,
            EditTitleAdapter("학교 이메일"),
            EditEmailAdapter(viewModel.email, false),
            emailStatusAdapter,

            )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.adapter =
            FillBottomButtonAdapter("회원가입", false, adapter) {
                viewModel.signup(email)
            }
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
        viewModel.signUpStatus.observe(this){
            if (it){
                activity?.finish()
                requireContext().showToast("회원가입이 되었습니다.")
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.userStatus.observe(this) {
            idStatusAdapter.setVisibility(it)
        }
        viewModel.passwordStatus.observe(this) {
            pwStatusAdapter.setVisibility(it)
        }
        viewModel.passwordVerifyStatus.observe(this) {
            pwVerifyAdapter.setVisibility(it)
        }
        viewModel.emailStatus.observe(this) {
            emailStatusAdapter.setVisibility(it)
        }
    }
}