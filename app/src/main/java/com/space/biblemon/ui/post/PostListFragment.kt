package com.space.biblemon.ui.post

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentPostListBinding

class PostListFragment : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private val viewModel: PostListViewModel by viewModels()



}