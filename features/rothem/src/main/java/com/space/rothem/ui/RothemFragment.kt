package com.space.rothem.ui

import androidx.fragment.app.viewModels
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RothemFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = RothemFragment()
    }

    private val viewModel: RothemViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "로뎀나무 예약")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.rothem.observe(viewLifecycleOwner){

        }

    }
}