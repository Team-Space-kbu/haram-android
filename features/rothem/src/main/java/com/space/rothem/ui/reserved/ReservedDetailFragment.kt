package com.space.rothem.ui.reserved

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.util.showToast
import com.space.rothem.BR
import com.space.rothem.R
import com.space.rothem.databinding.LayoutRothemCheckInBinding
import com.space.rothem.ui.reserved.adapter.BarcodeAdapter
import com.space.rothem.ui.reserved.adapter.QrcodeAdapter
import com.space.rothem.ui.reserved.adapter.ReservedHeaderAdapter
import com.space.rothem.ui.reserved.adapter.ShimmerReservedAdapter
import com.space.rothem.util.createBarcode
import com.space.rothem.util.createQrcode
import com.space.shared.data.core_ui.ImgHomeTitle
import com.space.shared.data.rothem.Reservation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedDetailFragment : ContainerCustomFragment<LayoutRothemCheckInBinding, Reservation>(
    R.layout.layout_rothem_check_in
) {

    companion object {
        fun newInstance() = ReservedDetailFragment()
    }

    override val viewModel: ReservedDetailViewModel by viewModels()
    private var adapter: RecyclerView.Adapter<*> = ShimmerReservedAdapter()

    override fun initView() {
        binding.setVariable(BR.title, "예약확인하기")
        binding.recyclerView.adapter = adapter
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            val data = it.data ?: return@observe
            adapter = ConcatAdapter(
                ReservedHeaderAdapter(
                    ImgHomeTitle(
                        data.roomResponse.roomName ?: "정보없음",
                        data.roomResponse.location?: "정보없음"
                    )
                ),
                BarcodeAdapter(createBarcode(data.reservationCode)),
                QrcodeAdapter(createQrcode(data.reservationCode))

            )
            binding.recyclerView.adapter = adapter
            binding.recyclerView.isNestedScrollingEnabled = false
            binding.cancelButton.setOnClickListener {
                viewModel.deleteReserved()
            }
        }
        viewModel.status.observe(this) {
            if (it) {
                requireContext().showToast("예약이 취소되었습니다.")
                setFragmentResult("updateUi", bundleOf("event" to true))
                parentFragmentManager.popBackStack()
            } else {
                requireContext().showToast("오류가 발생했습니다.")
            }
        }
    }

}