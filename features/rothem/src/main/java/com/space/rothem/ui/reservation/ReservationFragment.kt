package com.space.rothem.ui.reservation

import androidx.fragment.app.viewModels
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.rothem.Room
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {
    companion object {
        fun newInstance() = ReservationFragment()
    }

    private val roomSeq by extraNotNull<String>("reservation")
        .map { encodeString ->
            encodeString.decodeFromString<String>()
        }

    private val viewModel: ReservationViewModel by viewModels()

    override fun init() {
        roomSeq.let {
            viewModel.getReservationInfo(roomSeq)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, "예약하기")
        binding.lifecycleOwner = viewLifecycleOwner
    }


}