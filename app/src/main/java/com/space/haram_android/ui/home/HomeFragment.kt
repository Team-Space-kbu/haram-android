package com.space.haram_android.ui.home

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.common.data.ViewType
import com.space.haram_android.databinding.FragmentHomeBinding
import com.space.haram_android.ui.FunctionActivity
import com.space.haram_android.ui.LoginActivity
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeBannerViewPage.adapter = bannerAdapter
        binding.homeNewsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = newsAdapter
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
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
        binding.homeFunChapel.setOnClickListener {
            val intent = Intent(context,FunctionActivity::class.java)
            intent.apply {
                this.putExtra("viewType", ViewType.INTRANET_CHAPEL)
            }
            startActivity(intent)
        }
        binding.homeFunBook.setOnClickListener {
            val intent = Intent(context,FunctionActivity::class.java)
            intent.apply {
                this.putExtra("viewType", ViewType.BOOK_HOME)
            }
            startActivity(intent)
        }

    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.loginStatus.observe(viewLifecycleOwner, Observer {
            if (!it) {
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        })
        viewModel.homeInfo.observe(viewLifecycleOwner, Observer {
            binding.homeNoticeText.text = it!!.notice.notice[0].title
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

    override fun onDestroy() {
        super.onDestroy()
        sliderHandler.removeCallbacksAndMessages(null)

    }

}