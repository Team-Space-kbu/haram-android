package com.space.partners.ui.home.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.partners.BR
import com.space.partners.databinding.ItemPartnersBinding
import com.space.shared.data.partner.Partner

internal class PartnersAdapter(
    private val item: List<Partner>,
    private val paramsItemHandler: ParamsItemHandler<Partner>
) : RecyclerView.Adapter<PartnersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnersViewHolder =
        PartnersViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: PartnersViewHolder, position: Int) {
        holder.itemBind(item[position], paramsItemHandler)
    }

}

internal class PartnersViewHolder(
    private val binding: ItemPartnersBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): PartnersViewHolder {
            val binding =
                ItemPartnersBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return PartnersViewHolder(binding)
        }
    }

    fun itemBind(
        partner: Partner,
        paramsItemHandler: ParamsItemHandler<Partner>
    ) {
        binding.setVariable(BR.partners, partner)
        binding.setVariable(BR.partnersHandler, paramsItemHandler)
    }
}

