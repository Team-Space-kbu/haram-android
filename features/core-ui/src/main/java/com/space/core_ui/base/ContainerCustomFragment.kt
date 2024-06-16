package com.space.core_ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.space.core_ui.showToast
import com.space.shared.UiStatusType
import com.space.shared.type.AuthType
import timber.log.Timber

abstract class ContainerCustomFragment<VB : ViewDataBinding, T>(
    @LayoutRes override val layoutID: Int
) : BaseFragment<VB>(layoutID) {

    abstract val viewModel: BaseViewModel<T>
    protected var adapter= ConcatAdapter()

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

                else ->
                    requireContext().showToast("알 수 없는 오류가 발생했습니다.")

            }
        }

    }

    open fun beforeSuccessListener() {}
}

