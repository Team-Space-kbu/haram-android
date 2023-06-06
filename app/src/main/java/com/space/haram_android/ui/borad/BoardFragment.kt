package com.space.haram_android.ui.borad

import androidx.lifecycle.ViewModelProvider
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentBoardBinding
import com.space.haram_android.base.BaseFragment

class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    companion object {
        fun newInstance() = BoardFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this)[BoardViewModel::class.java]
    }

}