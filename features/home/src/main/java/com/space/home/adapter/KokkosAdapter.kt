package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import data.home.Kokkos
import com.space.home.databinding.ItemInfoKokkosBinding

internal class KokkosAdapter(
    private val kokkos: List<Kokkos>,
    private val itemHandler: KokkosItemAdapter.ItemHandler
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return KokkosViewHolder.newInstance(parent, kokkos, itemHandler)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as KokkosViewHolder).viewBind()
    }

    override fun getItemCount(): Int = 1

}

internal class KokkosViewHolder(
    private val binding: ItemInfoKokkosBinding,
    private val kokkos: List<Kokkos>,
    private val itemHandler: KokkosItemAdapter.ItemHandler
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
            kokkos: List<Kokkos>,
            itemHandler: KokkosItemAdapter.ItemHandler
        ): KokkosViewHolder {

            val binding =
                ItemInfoKokkosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return KokkosViewHolder(binding, kokkos, itemHandler)
        }
    }

    fun viewBind() {
        binding.recyclerViewKokkos.adapter = KokkosItemAdapter(kokkos, itemHandler)
        binding.recyclerViewKokkos.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}