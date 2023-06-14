package com.space.haram_android.ui.book

import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookInfoFragment : BaseFragment<FragmentBookInfoBinding>(R.layout.fragment_book_info) {

    companion object {
        fun newInstance() = BookInfoFragment()
    }

    private val viewModel: BookInfoViewModel by viewModels()


}