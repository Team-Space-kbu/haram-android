package com.space.haram_android.ui.chapel

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentChapelBinding
import com.space.haram_android.ui.base.BaseFragment

class ChapelFragment : BaseFragment<FragmentChapelBinding,ChapelViewModel>(R.layout.fragment_chapel) {

    companion object {
        fun newInstance() = ChapelFragment()
    }

    override fun init() {
        super.init()
        viewModel = ViewModelProvider(this).get(ChapelViewModel::class.java)
    }
}