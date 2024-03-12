package com.space.other

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.FuncAdapter
import com.space.core_ui.showToast
import com.space.other.adapter.LineAdapter
import com.space.other.adapter.SettingAdapter
import com.space.other.adapter.UserAdapter
import com.space.other.databinding.FragmentOtherBinding
import com.space.shared.UiStatusType
import com.space.shared.data.core_ui.Func
import com.space.shared.data.notice.NoticeViewType
import com.space.shared.data.other.SettingType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BaseFragment<FragmentOtherBinding>(
    R.layout.fragment_other
) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    private val viewModel: OtherViewModel by viewModels()

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun afterObserverListener() {
        viewModel.user.observe(viewLifecycleOwner) { it ->
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                val job = requireContext().getDrawable(R.drawable.ic_job)!!
                val bible = requireContext().getDrawable(R.drawable.ic_bible)!!
                val adapter = ConcatAdapter(
                    UserAdapter(it.data!!) {
                        requireContext().showToast("준비중입니다.")
                    },
                    FuncAdapter(Func(job, "취업정보")) {
                        viewModel.navigatorNotice.openView(requireContext(), NoticeViewType.JOB)
                    },
                    FuncAdapter(Func(bible, "교회(사역) 채용공고"))
                    {
                        viewModel.navigatorNotice.openView(requireContext(), NoticeViewType.BIBLE)
                    },
                    LineAdapter(),
                    SettingAdapter { type ->
                        when (type) {
                            SettingType.LOGOUT -> {
                                viewModel.logout()
                            }

                            else -> {

                            }
                        }

                    }
                )
                binding.recyclerView.adapter = adapter
            }
        }

        viewModel.logout.observe(viewLifecycleOwner) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                viewModel.navigatorLogin.openView(requireContext())
                activity?.finish()
                requireContext().showToast("로그아웃되었습니다.")
            } else {
                requireContext().showToast("로그아웃중 오류가 발생했습니다.")
            }
        }
    }


}