package com.space.rothem.ui.room

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.binding.adapter.DividerItemDecoration
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.view.FillBottomButtonAdapter
import com.space.core_ui.binding.adapter.image.ImageDescriptionAdapter
import com.space.core_ui.databinding.FragmentImgHomeBinding
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.transformFragment
import com.space.rothem.ui.reserved.ReservedFragment
import com.space.rothem.ui.room.adapter.RoomAmenitiesAdapter
import com.space.core_ui.binding.adapter.image.RoomHeaderAdapter
import com.space.rothem.ui.room.adapter.ShimmerRoomAdapter
import com.space.shared.data.core_ui.ImgHomeDescription
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.rothem.Room
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : ContainerCustomFragment<FragmentImgHomeBinding, RoomDetail>(
    R.layout.fragment_img_home
) {

    companion object {
        fun newInstance() = RoomFragment()
    }

    override val viewModel: RoomViewModel by viewModels()

    private val room by extraNotNull<String>("room")
        .map { encodeString ->
            encodeString.decodeFromString<Room>()
        }


    override fun init() {
        super.init()
        room.let {
            viewModel.onRothemDetail(it)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, room.roomName)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerRoomAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.vw_line_divider,
                resources.getDimensionPixelSize(R.dimen.margin_20dp),
                5,
                5
            )
        )
        binding.recyclerView.isNestedScrollingEnabled = false
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.view.observe(viewLifecycleOwner) {
            val data = it.data?:return@observe
            binding.setVariable(BR.imgUrl, data.roomResponse.thumbnailPath)
            val adapter = ConcatAdapter(
                RoomHeaderAdapter(
                    ImgHomeTitle(data.roomResponse.roomName, data.roomResponse.location)
                ),
                ImageDescriptionAdapter(
                    ImgHomeDescription("Description", data.roomResponse.roomExplanation)
                ),
                RoomAmenitiesAdapter(data.amenityResponses)
            )
            binding.recyclerView.adapter =
                FillBottomButtonAdapter("예약하기", false, adapter) {
                    parentFragmentManager.transformFragment<ReservedFragment>(
                        R.id.container,
                        "reservation" to data.roomResponse.roomSeq.toString()
                    )
                }
        }
    }
}