package com.space.rothem.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemInfoBinding

internal class InputInfoAdapter<T : RecyclerView.ViewHolder>(
    private val adapter: RecyclerView.Adapter<T>
) : RecyclerView.Adapter<InputInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputInfoViewHolder =
        InputInfoViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: InputInfoViewHolder, position: Int) =
        holder.itemBind(adapter)

}

internal class InputInfoViewHolder(
    private val binding: ItemRothemInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): InputInfoViewHolder {
            val binding =
                ItemRothemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return InputInfoViewHolder(binding)
        }
    }

    fun <T : RecyclerView.ViewHolder> itemBind(
        adapter: RecyclerView.Adapter<T>
    ) {
        binding.setVariable(BR.imgTitle, "날짜 선택")
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            itemView.context,
            RecyclerView.VERTICAL,
            false
        )
    }
}

