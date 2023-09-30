package com.space.biblemon.ui.timetable

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentTimeTableBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TimeTableFragment : BaseFragment<FragmentTimeTableBinding>(R.layout.fragment_time_table) {

    companion object {
        fun newInstance() = TimeTableFragment()
    }

    private val viewModel: TimeTableViewModel by viewModels()


}