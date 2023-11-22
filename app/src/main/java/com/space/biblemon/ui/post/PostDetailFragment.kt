package com.space.biblemon.ui.post

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentPostDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>(R.layout.fragment_post_detail) {

    companion object {
        fun newInstance() = PostDetailFragment()
    }

    private val viewModel: PostDetailViewModel by viewModels()


}