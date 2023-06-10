package com.space.haram_android.ui.chapel

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentChapelBinding
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.ui.intranet.IntranetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapelFragment : BaseFragment<FragmentChapelBinding>(R.layout.fragment_chapel) {

    companion object {
        fun newInstance() = ChapelFragment()
    }

    private val viewModel: ChapelViewModel by viewModels()

    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}