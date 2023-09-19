package com.space.haram_android.ui.book.info

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.haram_android.BR
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookDetailBinding
import com.space.haram_android.ui.book.search.BookSearchFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    companion object {
        fun newInstance() = BookDetailFragment()
    }

    private val viewModel: BookDetailViewModel by viewModels()

    override fun initView() {
        super.initView()
        toolbarTitle = "도서상세 정보"
        setFragmentResultListener("detailKey") { _, bundle ->
            bundle.getInt("pathUrl").let {
                viewModel.getBookDetail(it)
                viewModel.getBookKeep(it)
            }
        }
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() = with(viewModel){
        super.initListener()
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                setFragmentResult("detailKey", bundleOf("pathUrl" to it.viewPath))
                newFragmentView(BookDetailFragment())
                bindingViewListener.clearViewType()
            }
        }
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.serverStatus.observe(viewLifecycleOwner) {
            if (!it) {
                Toast.makeText(context, "검색한 데이터가 존재하지 않습니다", Toast.LENGTH_LONG).show()
                parentFragmentManager.popBackStack()
            }
        }
    }
}
