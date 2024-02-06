package com.space.rothem.ui.room

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.core_ui.base.BaseFragment
import com.space.rothem.R
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class RoomFragment : BaseFragment<>() {
//
//    companion object {
//        fun newInstance() = RoomFragment()
//    }
//
//    private lateinit var viewModel: RoomViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_room, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
//
//}