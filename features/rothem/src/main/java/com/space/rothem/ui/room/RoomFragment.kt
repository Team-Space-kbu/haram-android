package com.space.rothem.ui.room

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentImgHomeBinding
import com.space.rothem.ui.room.adapter.RoomAmenitiesAdapter
import com.space.rothem.ui.room.adapter.RoomDescriptionAdapter
import com.space.rothem.ui.room.adapter.RoomHeaderAdapter
import com.space.shared.data.core_ui.ImgHomeDescription
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.rothem.Room
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : BaseFragment<FragmentImgHomeBinding>(
    R.layout.fragment_img_home
) {

    companion object {
        fun newInstance() = RoomFragment()
    }

    private val viewModel: RoomViewModel by viewModels()
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
        super.initView()
        binding.setVariable(BR.title, room.roomName)
        binding.lifecycleOwner = viewLifecycleOwner
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
        super.afterObserverListener()
        viewModel.rothem.observe(viewLifecycleOwner) {
            binding.setVariable(BR.imgUrl, it.roomResponse.thumbnailPath)
            val adapter = ConcatAdapter(
                RoomHeaderAdapter(
                    ImgHomeTitle(it.roomResponse.roomName, it.roomResponse.location)
                ),
                RoomDescriptionAdapter(
                    ImgHomeDescription("Description", it.roomResponse.roomExplanation)
                ),
                RoomAmenitiesAdapter(it.amenityResponses)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}