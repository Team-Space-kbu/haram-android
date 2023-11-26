package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import data.home.Kokkos
import com.space.home.databinding.ItemKokkosImgBinding

internal class KokkosItemAdapter(
    private val kokkos: List<Kokkos>,
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<ItemKokkosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemKokkosViewHolder =
        ItemKokkosViewHolder.newInstance(parent, itemHandler)

    override fun getItemCount() = kokkos.size

    override fun onBindViewHolder(holder: ItemKokkosViewHolder, position: Int) =
        holder.bindItem(kokkos[position])

    interface ItemHandler {
        fun clickKokkos(kokkos: Kokkos)
    }
}

internal class ItemKokkosViewHolder(
    private val binding: ItemKokkosImgBinding,
    private val itemHandler: KokkosItemAdapter.ItemHandler
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            itemHandler: KokkosItemAdapter.ItemHandler
        ): ItemKokkosViewHolder {
            val binding = ItemKokkosImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemKokkosViewHolder(binding, itemHandler)
        }
    }

    fun bindItem(kokkos: Kokkos) {
        binding.kokkos = kokkos
        binding.homeNewsImage.setOnClickListener {
            itemHandler.clickKokkos(kokkos)
        }
    }

}
