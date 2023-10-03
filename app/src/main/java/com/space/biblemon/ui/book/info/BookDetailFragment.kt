package com.space.biblemon.ui.book.info

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.biblemon.BR
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentBookDetailBinding
import com.space.biblemon.util.FragmentFactory
import com.space.biblemon.util.ViewType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    companion object {
        fun newInstance() = BookDetailFragment()
    }

    private val viewModel: BookDetailViewModel by viewModels()


    override fun init() {
        super.init()
        toolbarTitle = "도서상세 정보"
        setFragmentResultListener("detailKey") { _, bundle ->
            val result = bundle.getInt("pathUrl")
            result.let {
                viewModel.getBookDetail(it)
                viewModel.getBookKeep(it)
                clearFragmentResultListener("detailKey")
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                bindingViewListener.clearViewType()
                setFragmentResult("detailKey", bundleOf("pathUrl" to it.viewPath))
                newFragmentView(FragmentFactory.createFragment(ViewType.BOOK_DETAIL)!!)
            }
        }
        serverStatus.observe(viewLifecycleOwner) {
            if (!it) {
                Toast.makeText(context, "검색한 데이터가 존재하지 않습니다", Toast.LENGTH_LONG).show()
                parentFragmentManager.popBackStack()
            }
        }
    }
}
