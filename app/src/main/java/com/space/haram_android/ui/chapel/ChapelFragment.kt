package com.space.haram_android.ui.chapel

import androidx.lifecycle.ViewModelProvider
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentChapelBinding
import com.space.haram_android.base.BaseFragment

class ChapelFragment : BaseFragment<FragmentChapelBinding>(R.layout.fragment_chapel) {

    companion object {
        fun newInstance() = ChapelFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this)[ChapelViewModel::class.java]
    }
}