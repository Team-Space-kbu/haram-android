package com.space.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.shared.data.home.Kokkos
import com.space.shared.data.home.Slider
import com.space.home.adapter.ShortcutAdapter
import com.space.home.adapter.KokkosAdapter
import com.space.home.adapter.KokkosItemAdapter
import com.space.home.adapter.NoticeAdapter
import com.space.home.adapter.SliderAdapter
import com.space.home.adapter.SliderItemAdapter
import com.space.home.databinding.FragmentHomeBinding
import com.space.home.util.ViewType
import com.space.home.util.startOpenPdf
import com.space.navigator.NavigatorBookInfo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var navigatorBookInfo: NavigatorBookInfo

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.homeInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                NoticeAdapter(
                    it.notice
                ),
                SliderAdapter(
                    it.slider,
                    object : SliderItemAdapter.ItemHandler {
                        override fun clickSlider(slider: Slider) {

                        }
                    }
                ),
                ShortcutAdapter(
                    object : ShortcutAdapter.ItemHandler {
                        override fun clickShortcut(viewType: ViewType) {
                            when(viewType){
                                ViewType.BOOK_HOME ->
                                    navigatorBookInfo.openBookInfo(requireContext())
                                else -> {}
                            }
                        }

                    }
                ),
                KokkosAdapter(
                    it.kokkos,
                    object : KokkosItemAdapter.ItemHandler {
                        override fun clickKokkos(kokkos: Kokkos) {
                            requireContext().startOpenPdf(kokkos)
                        }
                    }
                )
            )
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}