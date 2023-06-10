package com.space.haram_android.ui.timetable

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentTimeTableBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TimeTableFragment : BaseFragment<FragmentTimeTableBinding>(R.layout.fragment_time_table) {

    companion object {
        fun newInstance() = TimeTableFragment()
    }

    private val viewModel: TimeTableViewModel by viewModels()


}