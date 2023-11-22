package com.space.biblemon.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.space.biblemon.BR
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentHomeBinding
import com.space.biblemon.ui.FunctionActivity
import com.space.biblemon.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

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

    override fun init() {
        super.init()
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        viewModel.setOffsetPx(screenWidth - pageMarginPx - pagerWidth)
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.handler = sliderHandler
        binding.runnable = sliderImageRunnable
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeNewsRecycler.adapter = HomeNewsRecycler(viewModel.onClickKokkoks)
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        homeFormState.observe(viewLifecycleOwner) {
            if (it.loginStatus) {
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        }
        eventViewType.observe(viewLifecycleOwner) { type ->
            type.let {
                if (viewTypeVerify()) {
                    val intent = Intent(context, FunctionActivity::class.java)
                        .putExtra("viewType", it)
                    startActivity(intent)
                    bindingListener.clearViewType()
                }
            }
        }
        eventKokkoks.observe(viewLifecycleOwner) {
            val intent = Intent(Intent.ACTION_VIEW)
                .setDataAndType(Uri.parse(it), "application/pdf")
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


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