package com.space.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.data.response.home.data.Kokkos
import com.space.home.adapter.HeaderAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.KokkosItemAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.homeInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build(),
                HeaderAdapter(),
                NoticeAdapter(),
                SliderAdapter(it.banner.banners),
                KokkosAdapter(
                    it.kokkoks.kokkoksNews,
                    object : KokkosItemAdapter.ItemHandler {
                        override fun clickKokkos(kokkos: Kokkos) {

                        }
                    }
                )
            )
            binding.recyclerView.adapter = adapter
        }
    }
}