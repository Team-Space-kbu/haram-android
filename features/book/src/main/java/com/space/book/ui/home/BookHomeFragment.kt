package com.space.book.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R
import com.space.book.databinding.FragmentContainerBinding
import com.space.book.ui.home.adapter.BookItemAdapter
import com.space.book.ui.home.adapter.ItemBookAdapter
import com.space.book.ui.home.adapter.SearchAdapter
import com.space.book.ui.home.adapter.SliderAdapter
import com.space.book.ui.home.adapter.SliderItemAdapter
import com.space.core_ui.base.BaseFragment
import com.space.shared.data.Item
import com.space.shared.data.book.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookHomeFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()


    override fun initView() {
        super.initView()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.titleToolbar.text = "도서검색"
    }

    override fun initListener() {
        super.initListener()
        viewModel.bookHome.observe(viewLifecycleOwner) {
            val click = object : BookItemAdapter.ItemHandler{
                override fun clickCategory(category: Category) {

                }

            }
            val adapter = ConcatAdapter(
                SearchAdapter(),
                SliderAdapter(
                    Item(
                        list = it.image,
                    )
                ),
                ItemBookAdapter(
                    Item(
                        title = "인기도서",
                        list = it.bestBook
                    )
                ),
                ItemBookAdapter(
                    Item(
                        title = "신작도서",
                        list = it.newBook
                    )
                ),
                ItemBookAdapter(
                    Item(
                        title = "대여정보",
                        list = it.rentalBook
                    )
                ),

            )
            binding.recyclerView.adapter = adapter
        }
    }

}