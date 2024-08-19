package com.space.partners.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.binding.adapter.DividerItemDecoration
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.BR
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.image.ImageDescriptionAdapter
import com.space.core_ui.binding.adapter.image.ImageDescriptionBoxAdapter
import com.space.core_ui.binding.adapter.image.RoomHeaderAdapter
import com.space.core_ui.databinding.FragmentImgHomeBinding
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.partners.ui.detail.databinding.adapter.MapAdapter
import com.space.partners.ui.detail.databinding.adapter.ShimmerDetailAdapter
import com.space.shared.data.core_ui.ImgHomeDescription
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.partner.Partner
import com.space.shared.data.partner.PartnersDetail
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnersDetailFragment : ContainerCustomFragment<FragmentImgHomeBinding, PartnersDetail>(
    R.layout.fragment_img_home
) {

    companion object {
        fun newInstance() = PartnersDetailFragment()
    }

    override val viewModel: PartnersDetailViewModel by viewModels()
    private var adapter: RecyclerView.Adapter<*> = ShimmerDetailAdapter()
    private val partner by extraNotNull<String>("partner")
        .map { it.decodeFromString<Partner>() }

    override fun init() {
        viewModel.partnersDetail(partner.id.toString())
    }

    override fun initView() {
        binding.setVariable(BR.title, "제휴업체")
        binding.setVariable(BR.imgUrl, partner.image)
        binding.setVariable(BR.imageHandler,
            NonParamsItemHandler {
                viewModel.navigatorImage.openView(requireContext(), partner.image)
            }
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.line_divider,
                5,
                5,
                specificIndex = 1
            )
        )
        binding.recyclerView.adapter = adapter
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            RoomHeaderAdapter(
                ImgHomeTitle(data.businessName, data.address)
            ),
            ImageDescriptionAdapter(
                ImgHomeDescription("소개", data.description)
            ),
            ImageDescriptionBoxAdapter(
                ImgHomeDescription("혜택", data.benefits)
            ),
            MapAdapter(
                childFragmentManager,
                data.xCoordinate,
                data.yCoordinate
            )
        )
        binding.recyclerView.adapter = adapter
    }


}