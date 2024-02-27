package com.space.rothem.ui.room

import androidx.fragment.app.viewModels
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.rothem.R
import com.space.rothem.databinding.FragmentRoomBinding
import com.space.shared.data.rothem.Room
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : BaseFragment<FragmentRoomBinding>(R.layout.fragment_room) {

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
        binding.setVariable(BR.title, "스터디룸1")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.rothem.observe(viewLifecycleOwner){
            binding.setVariable(com.space.rothem.BR.detail, it)
        }
    }

}