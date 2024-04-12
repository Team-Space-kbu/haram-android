package com.space.core_ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.space.core_ui.BR
import com.space.core_ui.showToast
import com.space.shared.UiStatusType
import com.space.shared.type.AuthType

abstract class ContainerCustomFragment<VB : ViewDataBinding, T>(
    @LayoutRes override val layoutID: Int
) : BaseFragment<VB>(layoutID) {

    abstract val viewModel: BaseViewModel<T>

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS->{

                }

                UiStatusType.LOGOUT -> {
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }
                UiStatusType.REJECT->{
                    viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                    activity?.finish()
                }

                UiStatusType.NO_CONNECTION ->
                    requireContext().showToast("인터넷 연결상태가 좋지 않아 연결할 수 없습니다.")

                else ->
                    requireContext().showToast("알 수 없는 오류가 발생했습니다.")

            }
        }
    }
}

