package com.space.haram_android.ui.book.search



import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookSearchFragment : BaseFragment<FragmentBookSearchBinding>(R.layout.fragment_book_search) {

    companion object {
        fun newInstance() = BookSearchFragment()
    }

    private val viewModel: BookSearchViewModel by viewModels()

    override fun initView() {
        super.initView()
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            result?.let { viewModel.getSearchList(it) }
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }


}

