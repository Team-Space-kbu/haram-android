package com.space.rothem.ui.reserved

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.showToast
import com.space.rothem.BR
import com.space.rothem.R
import com.space.rothem.databinding.LayoutRothemCheckInBinding
import com.space.rothem.ui.reserved.adapter.BarcodeAdapter
import com.space.rothem.ui.reserved.adapter.QrcodeAdapter
import com.space.rothem.ui.reserved.adapter.ReservedHeaderAdapter
import com.space.rothem.util.createBarcode
import com.space.rothem.util.createQrcode
import com.space.shared.data.core_ui.ImgHomeTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedDetailFragment : BaseFragment<LayoutRothemCheckInBinding>(
    R.layout.layout_rothem_check_in
) {

    companion object {
        fun newInstance() = ReservedDetailFragment()
    }

    private val viewModel: ReservedDetailViewModel by viewModels()


    override fun initView() {
        binding.setVariable(BR.title, "예약확인하기")
    }


    override fun afterObserverListener() {
        viewModel.reserved.observe(this) {
            val adapter = ConcatAdapter(
                ReservedHeaderAdapter(
                    ImgHomeTitle(it.roomResponse.roomName, it.roomResponse.location)
                ),
                BarcodeAdapter(createBarcode(it.reservationCode)),
                QrcodeAdapter(createQrcode(it.reservationCode))

            )
            binding.recyclerView.adapter = adapter
            binding.recyclerView.isNestedScrollingEnabled = false
            binding.cancelButton.setOnClickListener {
                viewModel.deleteReserved()
            }
        }
        viewModel.status.observe(this){
            if (it){
                requireContext().showToast("예약이 취소되었습니다.")
                setFragmentResult("updateUi", bundleOf("event" to true))
                parentFragmentManager.popBackStack()
            }else{
                requireContext().showToast("오류가 발생했습니다.")
            }
        }
    }

}