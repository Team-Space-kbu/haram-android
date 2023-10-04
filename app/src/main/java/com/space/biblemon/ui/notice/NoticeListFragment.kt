package com.space.biblemon.ui.notice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.biblemon.R

class NoticeListFragment : Fragment() {

    companion object {
        fun newInstance() = NoticeListFragment()
    }

    private lateinit var viewModel: NoticeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoticeListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}