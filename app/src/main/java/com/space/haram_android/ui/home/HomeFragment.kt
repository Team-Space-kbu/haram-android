package com.space.haram_android.ui.home

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentHomeBinding
import com.space.haram_android.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private val sliderHandler: Handler = Handler(Looper.getMainLooper())
    private val sliderImageRunnable =
        Runnable {
            binding.homeBannerViewPage.currentItem = binding.homeBannerViewPage.currentItem + 1
        }

    override fun initView() {
        super.initView()
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        viewModel.setOffsetPx(screenWidth - pageMarginPx - pagerWidth)
        binding.runnable = sliderImageRunnable
        binding.handler = sliderHandler
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            if (!it) {
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderImageRunnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderImageRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        sliderHandler.removeCallbacksAndMessages(null)

    }

}