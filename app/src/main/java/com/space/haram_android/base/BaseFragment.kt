package com.space.haram_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.space.haram_android.ui.login.LoginViewModel
import dagger.Binds
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

abstract class BaseFragment<binding_ : ViewDataBinding, viewModel_ : ViewModel>(@LayoutRes val LayoutID: Int) :
    Fragment() {

    protected lateinit var binding: binding_
    protected lateinit var viewModel: viewModel_

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, LayoutID, container, false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    protected open fun init(): Unit {}
    protected open fun initView(): Unit {}
    protected open fun initViewData() {}
    protected open fun initListener() {}
}