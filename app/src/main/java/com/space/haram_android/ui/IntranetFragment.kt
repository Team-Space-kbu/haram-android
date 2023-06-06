package com.space.haram_android.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R

class IntranetFragment : Fragment() {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private lateinit var viewModel: IntranetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intranet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntranetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}