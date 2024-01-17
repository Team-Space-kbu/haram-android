package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.shared.data.home.Kokkos
import com.space.home.databinding.ItemInfoKokkosBinding


internal class KokkosAdapter(
    private val kokkos: List<Kokkos>,
    private val itemHandler: ParamsItemHandler<Kokkos>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return KokkosViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as KokkosViewHolder).viewBind(kokkos, itemHandler)
    }

    override fun getItemCount(): Int = 1

}

internal class KokkosViewHolder(
    private val binding: ItemInfoKokkosBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): KokkosViewHolder {

            val binding =
                ItemInfoKokkosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return KokkosViewHolder(binding)
        }
    }

    fun viewBind(
        kokkos: List<Kokkos>,
        itemHandler: ParamsItemHandler<Kokkos>
    ) {
        binding.recyclerViewKokkos.adapter = KokkosItemAdapter(kokkos, itemHandler)
        binding.recyclerViewKokkos.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
    }
}