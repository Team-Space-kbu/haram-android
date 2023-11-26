package com.space.biblemon.ui.intranet.chapel

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.biblemon.BR
import com.space.biblemon.R
import com.space.biblemon.databinding.FragmentChapelBinding
import com.space.biblemon.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapelFragment : BaseFragment<FragmentChapelBinding>(R.layout.fragment_chapel) {

    companion object {
        fun newInstance() = ChapelFragment()
    }

    private val viewModel: ChapelViewModel by viewModels()
    private lateinit var chapelListRecycler: ChapelListRecycler


    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "채플정보"
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.chapelInfo.observe(viewLifecycleOwner, Observer {
            binding.invalidateAll()
        })
    }

}