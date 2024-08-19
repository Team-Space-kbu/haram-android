package com.space.core_ui.base


import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.util.showToast
import com.space.shared.UiStatusType
import com.space.shared.type.AuthType


abstract class ContainerFragment<T> : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {
    abstract val viewModel: BaseViewModel<T>
    abstract val viewTitle: String


    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, viewTitle)
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS -> beforeSuccessListener()

                UiStatusType.LOGOUT -> {
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                UiStatusType.REJECT -> {
                    viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                    activity?.finish()
                }

                UiStatusType.NO_CONNECTION ->
                    requireContext().showToast("인터넷 연결상태가 좋지 않아 연결할 수 없습니다.")

                UiStatusType.EMPTY -> beforeEmptyListener()

                UiStatusType.LOADING -> {}

                else ->
                    requireContext().showToast("알 수 없는 오류가 발생했습니다.")

            }
        }
    }

    open fun beforeEmptyListener() {}

    open fun beforeSuccessListener() {}
}

