package com.space.rothem.ui.home

import androidx.core.view.setPadding
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.transformFragment
import com.space.rothem.ui.home.adapter.HeaderAdapter
import com.space.rothem.ui.home.adapter.NoticeAdapter
import com.space.rothem.ui.home.adapter.ReservedAdapter
import com.space.rothem.ui.home.adapter.RoomsItemAdapter
import com.space.rothem.ui.home.adapter.ShimmerHomeAdapter
import com.space.rothem.ui.reserved.ReservedDetailFragment
import com.space.rothem.ui.room.RoomFragment
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.encodeToString
import com.space.shared.type.NoticeSpaceType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RothemFragment : ContainerFragment() {

    companion object {
        fun newInstance() = RothemFragment()
    }

    override val viewModel: RothemViewModel by viewModels()
    override val viewTitle: String = "로뎀나무 예약"

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerHomeAdapter()
        binding.recyclerView.setPadding(0)
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
        viewModel.view.observe(viewLifecycleOwner) {
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                NoticeAdapter(data.noticeResponses) { notice ->
                    viewModel.navigatorNoticeSpace.openView(
                        requireContext(),
                        SpaceNoticeData(
                            notice.noticeSeq.toString(),
                            NoticeSpaceType.ROTHEM
                        )
                    )
                },
                ReservedAdapter(data.isReserved) {
                    parentFragmentManager.transformFragment<ReservedDetailFragment>(
                        R.id.container
                    )
                },
                HeaderAdapter(),
                RoomsItemAdapter(data.roomResponses) { room ->
                    parentFragmentManager.transformFragment<RoomFragment>(
                        R.id.container,
                        "room" to room.encodeToString()
                    )
                }
            )
            binding.recyclerView.adapter = adapter
        }
        setFragmentResultListener("updateUi") { _, bundle ->
            val result = bundle.getBoolean("event", false)
            if (result) {
                viewModel.getRothemHome()
            }
        }
    }
}