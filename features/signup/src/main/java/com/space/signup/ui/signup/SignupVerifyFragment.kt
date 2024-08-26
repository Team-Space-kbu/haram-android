package com.space.signup.ui.signup

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.google.firebase.analytics.FirebaseAnalytics
import com.space.core_ui.extension.EditType
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.item.input.EditTextAdapter
import com.space.core_ui.binding.adapter.view.FillBottomButtonAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.shared.decodeFromString
import com.space.shared.model.EmailModel
import com.space.signup.ui.binding.adapter.EditStatusAdapter
import com.space.core_ui.binding.adapter.item.input.EditTitleAdapter
import com.space.core_ui.extension.logEvent
import com.space.shared.model.UserTerms
import com.space.signup.ui.find.InfoHeaderAdapter
import com.space.signup.ui.email.adapter.EditEmailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupVerifyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = SignupVerifyFragment()
    }

    private val email by extraNotNull<String>("email")
        .map { it.decodeFromString<EmailModel>() }
    private val policy by extraNotNull<String>("policy")
        .map { it.decodeFromString<List<UserTerms>>() }
    private val color = Color.parseColor("#E82722")
    private val viewModel: SignupVerifyViewModel by viewModels()


    private val idStatusAdapter by lazy {
        EditStatusAdapter("아이디가 이미 존재합니다.", viewModel.userStatus, color, false)
    }
    private val nicknameStatusAdapter by lazy {
        EditStatusAdapter("닉네임은 한글 숫자 영어만 가능합니다.", viewModel.nickNameStatus, color, false)
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
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_none)
            )
        )
        binding.recyclerView.adapter =
            FillBottomButtonAdapter("회원가입", false, adapter) {
                viewModel.signup(email, policy)
            }
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
        viewModel.signUpStatus.observe(this){
            if (it){
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP) {
                    param("UserEmail", viewModel.email.value.toString())
                }
                activity?.finish()
                requireContext().showToast("회원가입이 되었습니다.")
            }
        }
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