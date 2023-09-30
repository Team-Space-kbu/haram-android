package com.space.biblemon.ui.post

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {

    companion object {
        fun newInstance() = PostFragment()
    }
    private val viewModel: PostViewModel by viewModels()



}