package com.space.book.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.space.book.R
import com.space.book.databinding.FragmentBookHomeBinding


class BookFragment : Fragment() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()
    private lateinit var binding: FragmentBookHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}