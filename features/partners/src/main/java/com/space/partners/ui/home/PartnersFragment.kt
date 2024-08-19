package com.space.partners.ui.home


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.binding.adapter.DividerItemDecoration
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.extension.transformFragment
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
    private var adapter: RecyclerView.Adapter<*> = ShimmerHomeAdapter()

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.line_divider,
                5,
                5
            )
        )
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        adapter = PartnersAdapter(data) { partner ->
            parentFragmentManager.transformFragment<PartnersDetailFragment>(
                R.id.container,
                "partner" to partner.encodeToString()
            )
        }
        binding.recyclerView.adapter = adapter
    }


}