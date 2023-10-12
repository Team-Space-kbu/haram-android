package com.space.biblemon.ui.rothem

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentRothemBinding

class RothemFragment : BaseFragment<FragmentRothemBinding>(R.layout.fragment_rothem) {

    companion object {
        fun newInstance() = RothemFragment()
    }

    private val viewModel: RothemViewModel by viewModels()


}