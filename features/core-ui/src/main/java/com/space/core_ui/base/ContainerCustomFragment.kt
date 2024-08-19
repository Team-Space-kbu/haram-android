package com.space.core_ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.space.core_ui.util.showToast
import com.space.shared.UiStatusType
import com.space.shared.type.AuthType

abstract class ContainerCustomFragment<VB : ViewDataBinding, T>(
    @LayoutRes override val layoutID: Int
) : BaseFragment<VB>(layoutID) {

    protected abstract val viewModel: BaseViewModel<T>

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS -> beforeSuccessListener()

                UiStatusType.LOGOUT -> {
                    requireContext().showToast("로그아웃되었습니다.")
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                UiStatusType.REJECT -> {
                    viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                    activity?.finish()
                }

                UiStatusType.NO_CONNECTION ->
                    requireContext().showToast("인터넷 연결상태가 좋지 않아 연결할 수 없습니다.")

                UiStatusType.LOADING -> {}

                UiStatusType.EMPTY -> beforeEmptyListener()

                else ->
                    requireContext().showToast("알 수 없는 오류가 발생했습니다.")

            }
        }

    }

    open fun beforeEmptyListener() {}

    open fun beforeSuccessListener() {}
}

