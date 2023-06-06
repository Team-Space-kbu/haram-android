package com.space.haram_android.ui.home

import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentHomeBinding
import com.space.haram_android.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var bannerAdapter: HomeBannerRecycler
    private lateinit var newsAdapter: HomeNewsRecycler
    private val viewModel: HomeViewModel by viewModels()
    private val sliderHandler: Handler = Handler(Looper.getMainLooper())
    private val sliderImageRunnable =
        Runnable {
            binding.homeBannerViewPage.currentItem = binding.homeBannerViewPage.currentItem + 1
        }

    override fun initView() {
        super.initView()
        bannerAdapter = HomeBannerRecycler()
        newsAdapter = HomeNewsRecycler()
        binding.homeBannerViewPage.adapter = bannerAdapter
        binding.homeNewsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = newsAdapter
        }
        super.init()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun initListener() {
        super.initListener()
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth
        binding.homeBannerViewPage.apply {
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderImageRunnable)
                    sliderHandler.postDelayed(sliderImageRunnable, 3000)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
            setPageTransformer { view, position ->
                view.translationX = position * -offsetPx
            }
        }
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            if (!it) {
                parentFragmentManager.beginTransaction().replace(R.id.container, LoginFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                    .commit()
            }
        })
        viewModel.homeInfo.observe(viewLifecycleOwner, Observer {
            binding.homeNoticeText.text = it.notice.notice[0].title
            it.banner.banners.forEach { i -> bannerAdapter.addItem(i) }
            it.kokkoks.kbuNews.forEach { i -> newsAdapter.addItem(i) }
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


}