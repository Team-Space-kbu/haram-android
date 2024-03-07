package com.space.rothem.ui.reservation

import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationDetailFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {

    companion object {
        fun newInstance() = ReservationDetailFragment()
    }

    private val viewModel: ReservationDetailViewModel by viewModels()



}