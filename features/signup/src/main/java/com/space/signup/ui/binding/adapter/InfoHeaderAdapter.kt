package com.space.signup.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.signup.BR
import com.space.signup.databinding.ItemSignTitleBinding

class InfoHeaderAdapter(
    private val title: String,
    private val text: String
) : RecyclerView.Adapter<InfoHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHeaderViewHolder {
        return InfoHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: InfoHeaderViewHolder, position: Int) =
        holder.itemBind(title, text)

    override fun getItemCount(): Int = 1

}

class InfoHeaderViewHolder(
    private val binding: ItemSignTitleBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): InfoHeaderViewHolder {
            val binding = ItemSignTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return InfoHeaderViewHolder(binding)
        }
    }

    fun itemBind(
        title: String,
        text: String
    ) {
        binding.setVariable(BR.titleSignup, title)
        binding.setVariable(BR.textSignup, text)

    }
}