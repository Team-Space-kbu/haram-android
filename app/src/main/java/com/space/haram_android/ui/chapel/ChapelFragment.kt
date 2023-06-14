package com.space.haram_android.ui.chapel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentChapelBinding
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.ui.home.HomeNewsRecycler
import com.space.haram_android.ui.intranet.IntranetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

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
        binding.viewModel = viewModel
        chapelListRecycler = ChapelListRecycler()
        binding.ChapelList.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = chapelListRecycler
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.chapelInfo.observe(viewLifecycleOwner, Observer {
            binding.invalidateAll()
        })
        viewModel.chapelList.observe(viewLifecycleOwner, Observer {
            runBlocking {
                it.forEach { i -> chapelListRecycler.addItem(i) }
            }
            chapelListRecycler.notifyDataSetChanged()
        })
    }

}