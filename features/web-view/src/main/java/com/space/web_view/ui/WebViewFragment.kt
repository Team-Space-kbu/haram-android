package com.space.web_view.ui

import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.shared.decodeFromString
import com.space.web_view.BR
import com.space.web_view.R
import com.space.web_view.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>(
    R.layout.fragment_web_view
) {

    companion object {
        fun newInstance() = WebViewFragment()
    }

    private val url by extraNotNull<String>("webView").map {
        it.decodeFromString<Pair<String,String>>()
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, url.first)
        binding.webView.settings.apply{
            javaScriptEnabled= true                     // 자바스크립트 사용여부
            setSupportMultipleWindows(true)             // 새창 띄우기 허용여부
            javaScriptCanOpenWindowsAutomatically= true // 자바스크립트가 window.open()을 사용할 수 있도록 설정
            loadWithOverviewMode= true                  // html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            useWideViewPort= true                       // 화면 사이즈 맞추기 허용여부
            domStorageEnabled= true                     // DOM(html 인식) 저장소 허용여부
        }
        binding.webView.loadUrl(url.second)
    }

}