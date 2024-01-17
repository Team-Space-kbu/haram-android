package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.book.BR
import com.space.core_ui.ParamsItemHandler
import com.space.shared.data.home.Kokkos
import com.space.home.databinding.ItemKokkosImgBinding

internal class KokkosItemAdapter(
    private val kokkos: List<Kokkos>,
    private val itemHandler: ParamsItemHandler<Kokkos>
) : RecyclerView.Adapter<ItemKokkosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemKokkosViewHolder =
        ItemKokkosViewHolder.newInstance(parent)

    override fun getItemCount() = kokkos.size

    override fun onBindViewHolder(holder: ItemKokkosViewHolder, position: Int) =
        holder.bindItem(kokkos[position], itemHandler)


}

internal class ItemKokkosViewHolder(
    private val binding: ItemKokkosImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ItemKokkosViewHolder {
            val binding =
                ItemKokkosImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemKokkosViewHolder(binding)
        }
    }

    fun bindItem(kokkos: Kokkos, itemHandler: ParamsItemHandler<Kokkos>) {
        binding.setVariable(BR.kokkos, kokkos)
        binding.setVariable(BR.handlerKokkos, itemHandler)
    }

}
