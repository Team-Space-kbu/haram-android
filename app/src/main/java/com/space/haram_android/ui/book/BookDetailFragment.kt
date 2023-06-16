package com.space.haram_android.ui.book

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentBookDetailBinding
import com.space.haram_android.util.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(R.layout.fragment_book_detail) {

    companion object {
        fun newInstance() = BookDetailFragment()
    }

    private val viewModel: BookDetailViewModel by viewModels()
    private lateinit var detailKeepRecycler: BookDetailKeepRecycler

    override fun initView() {
        super.initView()
        setFragmentResultListener("detailKey") { _, bundle ->
            val result = bundle.getString("pathUrl")
            result?.let { viewModel.getBookDetail(it) }
        }
        detailKeepRecycler = BookDetailKeepRecycler()
        binding.bookDetailKeepRecycler.apply {
            adapter = detailKeepRecycler
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    R.drawable.line_divider,
                    50,
                    50
                )
            )
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.detailForm.observe(viewLifecycleOwner, Observer {
            binding.bookDetailInfo = it!!.bookInfoRes
            it.bookKeep.forEach { i ->
                detailKeepRecycler.addItem(i)
            }
            detailKeepRecycler.notifyDataSetChanged()
            Glide.with(this@BookDetailFragment).load(it.bookInfoRes.image).centerCrop()
                .into(binding.bookDetailImage)
        })
        viewModel.serverStatus.observe(viewLifecycleOwner, Observer {
            if (!it) {
                Toast.makeText(context, "검색한 데이터가 존재하지 않습니다", Toast.LENGTH_LONG).show()
                parentFragmentManager.popBackStack()
            }

        })
    }
}