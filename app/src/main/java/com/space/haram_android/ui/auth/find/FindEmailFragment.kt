package com.space.haram_android.ui.auth.find

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R

class FindEmailFragment : Fragment() {

    companion object {
        fun newInstance() = FindEmailFragment()
    }

    private lateinit var viewModel: FindEmailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_email, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindEmailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}