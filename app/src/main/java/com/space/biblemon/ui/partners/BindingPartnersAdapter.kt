package com.space.biblemon.ui.partners

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import data.partner.Partner

object BindingPartnersAdapter {
    @JvmStatic
    @BindingAdapter(value = ["partnersAdapter"])
    fun setPartnersItems(
        recyclerView: RecyclerView,
        item: MutableList<Partner>?,
    ) {
        recyclerView.adapter ?: run {
            recyclerView.adapter = PartnersRecycler()
        }
        recyclerView.adapter.let {
            if (it is PartnersRecycler) {
                item?.let { list -> it.addItem(list) }
            }
        }
    }
}