package com.space.haram_android.ui.post

import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    companion object {
        fun newInstance() = PostFragment()
    }
    private val viewModel: PostViewModel by viewModels()



}