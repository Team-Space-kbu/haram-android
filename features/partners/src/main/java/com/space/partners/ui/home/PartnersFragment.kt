package com.space.partners.ui.home


import androidx.fragment.app.viewModels
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.transformFragment
import com.space.partners.ui.detail.PartnersDetailFragment
import com.space.partners.ui.home.databinding.adapter.PartnersAdapter
import com.space.partners.ui.home.databinding.adapter.ShimmerHomeAdapter
import com.space.shared.data.partner.Partner
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnersFragment : ContainerFragment<List<Partner>>() {

    companion object {
        fun newInstance() = PartnersFragment()
    }

    override val viewModel: PartnersViewModel by viewModels()
    override val viewTitle: String = "제휴업체"

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerHomeAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.line_divider,
                5,
                5
            )
        )
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