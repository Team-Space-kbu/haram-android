package com.space.core_ui.binding.adapter.view

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemHeaderVerticalBinding

class HeaderVerticalAdapter(
    private val title: String,
    private val titleSize: Float,
    private val adapter: RecyclerView.Adapter<*>,
) : RecyclerView.Adapter<HeaderRecyclerviewViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderRecyclerviewViewHolder =
        HeaderRecyclerviewViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderRecyclerviewViewHolder, position: Int) =
        holder.itemBind(title, titleSize, adapter)

}

class HeaderRecyclerviewViewHolder(
    private val binding: ItemHeaderVerticalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): HeaderRecyclerviewViewHolder {
            val binding = ItemHeaderVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HeaderRecyclerviewViewHolder(binding)
        }
    }

    fun itemBind(
        title: String,
        titleSize: Float,
        adapter: RecyclerView.Adapter<*>
    ) {
        binding.setVariable(BR.title, title)
        binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
        binding.recyclerView.adapter = adapter
    }
}

