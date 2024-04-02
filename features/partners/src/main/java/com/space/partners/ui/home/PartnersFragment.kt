package com.space.partners.ui.home


import androidx.fragment.app.viewModels
import com.space.partners.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.transformFragment
import com.space.partners.ui.detail.PartnersDetailFragment
import com.space.partners.ui.home.databinding.adapter.PartnersAdapter
import com.space.partners.ui.home.databinding.adapter.ShimmerHomeAdapter
import com.space.shared.UiStatusType
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnersFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {

    companion object {
        fun newInstance() = PartnersFragment()
    }

    private val viewModel: PartnersViewModel by viewModels()


    override fun initView() {
        binding.setVariable(BR.title, "제휴업체")
        binding.recyclerView.adapter = ShimmerHomeAdapter()
    }

    override fun beforeObserverListener() {
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.LOGOUT) {
                activity?.finishAffinity()
                viewModel.navigatorLogin.openView(requireContext())
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            val data = it.data ?: return@observe
            binding.recyclerView.adapter = PartnersAdapter(data) { partner ->
                parentFragmentManager.transformFragment<PartnersDetailFragment>(
                    R.id.container,
                    "partner" to partner.encodeToString()
                )
            }
        }
    }

}