package com.space.rothem.ui.room

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.view.FillBottomButtonAdapter
import com.space.core_ui.binding.adapter.image.ImageDescriptionAdapter
import com.space.core_ui.databinding.FragmentImgHomeBinding
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.transformFragment
import com.space.rothem.ui.reserved.ReservedFragment
import com.space.core_ui.binding.adapter.image.ImageHeaderAdapter
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.rothem.ui.room.adapter.AmenitiesItemAdapter
import com.space.rothem.ui.room.adapter.ShimmerRoomAdapter
import com.space.shared.data.core_ui.ImgHomeDescription
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.rothem.Room
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.decodeFromString
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType
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


    override fun init() = viewModel.onRothemDetail(room)

    override fun initView() {
        binding.setVariable(BR.title, room.roomName)
        binding.recyclerView.adapter = ShimmerRoomAdapter()
        binding.recyclerView.setPadding(50, 0, 50, 0)
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.view.observe(viewLifecycleOwner) {
            val data = it.data ?: return@observe
            binding.setVariable(BR.imgUrl, data.roomResponse?.thumbnailPath ?: "")
            val adapter = ConcatAdapter(
                ImageHeaderAdapter(
                    ImgHomeTitle(
                        data.roomResponse?.roomName ?: "정보없음",
                        data.roomResponse?.location ?: "정보없음"
                    )
                ),
                ImageDescriptionAdapter(
                    ImgHomeDescription(
                        "Description",
                        data.roomResponse?.roomExplanation ?: "정보없음"
                    )
                ),
                ItemHeaderAdapter(
                    title = "Popular amenities",
                    titleSize = 18f,
                    adapter = AmenitiesItemAdapter(data.amenityResponses ?: emptyList()),
                    layoutType = LayoutType.FLEX,
                    padding = false
                ),

            )
            binding.recyclerView.adapter =
                FillBottomButtonAdapter("예약하기", true, adapter) {
                    parentFragmentManager.transformFragment<ReservedFragment>(
                        R.id.container,
                        "reservation" to data.roomResponse?.roomSeq.toString()
                    )
                }
        }
    }
}