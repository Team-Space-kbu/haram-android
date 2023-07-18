package com.space.haram_android.ui.book.info

import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.space.haram_android.BR
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    companion object {
        fun newInstance() = BookDetailFragment()
    }

    private val viewModel: BookDetailViewModel by viewModels()

    override fun initView() {
        super.initView()
        setFragmentResultListener("detailKey") { _, bundle ->
            val result = bundle.getInt("pathUrl")
            result.let {
                viewModel.getBookDetail(it)
                viewModel.getBookKeep(it)
            }
        }
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        activity?.findViewById<TextView>(R.id.function_toolbar_title)?.text = "도서상세 정보"
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