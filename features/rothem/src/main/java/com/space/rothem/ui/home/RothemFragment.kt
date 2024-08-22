package com.space.rothem.ui.home

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.core_ui.extension.transformFragment
import com.space.rothem.ui.home.adapter.ReservedAdapter
import com.space.rothem.ui.home.adapter.RoomsItemAdapter
import com.space.rothem.ui.home.adapter.ShimmerHomeAdapter
import com.space.rothem.ui.reserved.ReservedDetailFragment
import com.space.rothem.ui.room.RoomFragment
import com.space.shared.data.rothem.Rothem
import com.space.shared.encodeToString
import com.space.shared.type.DividerType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RothemFragment : ContainerFragment<Rothem>() {

    companion object {
        fun newInstance() = RothemFragment()
    }

    override val viewModel: RothemViewModel by viewModels()
    override val viewTitle: String = "로뎀나무"

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerHomeAdapter()
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )
    }

    override fun afterObserverListener() {
        viewModel.view.observe(viewLifecycleOwner) {
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                ReservedAdapter(data.isReserved) {
                    parentFragmentManager.transformFragment<ReservedDetailFragment>(
                        R.id.container
                    )
                },
                ItemHeaderAdapter(
                    title = "스터디룸 예약",
                    titleSize = 18f,
                    adapter = RoomsItemAdapter(data.roomResponses) { room ->
                        parentFragmentManager.transformFragment<RoomFragment>(
                            R.id.container,
                            "room" to room.encodeToString()
                        )
                    },
                    padding = false
                )
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