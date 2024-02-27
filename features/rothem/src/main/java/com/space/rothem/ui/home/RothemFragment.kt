package com.space.rothem.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.transformFragment
import com.space.rothem.ui.home.adapter.HeaderAdapter
import com.space.rothem.ui.home.adapter.NoticeAdapter
import com.space.rothem.ui.home.adapter.RoomsItemAdapter
import com.space.rothem.ui.room.RoomFragment
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RothemFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = RothemFragment()
    }

    private val viewModel: RothemViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "로뎀나무 예약")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.rothem.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                NoticeAdapter(it.noticeResponses) {

                },
                HeaderAdapter(),
                RoomsItemAdapter(it.roomResponses) { room ->
                    parentFragmentManager.transformFragment<RoomFragment>(
                        R.id.container,
                        "room" to room.encodeToString()
                    )
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }
}