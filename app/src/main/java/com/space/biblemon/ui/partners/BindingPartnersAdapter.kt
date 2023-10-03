package com.space.biblemon.ui.partners

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.biblemon.ui.book.home.BookBannerRecycler
import com.space.data.res.partners.PartnersReq

object BindingPartnersAdapter {
    @JvmStatic
    @BindingAdapter(value = ["partnersAdapter"])
    fun setPartnersItems(
        recyclerView: RecyclerView,
        item: MutableList<PartnersReq>?,
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