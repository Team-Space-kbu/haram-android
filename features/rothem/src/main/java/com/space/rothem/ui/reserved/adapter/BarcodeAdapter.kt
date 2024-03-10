package com.space.rothem.ui.reserved.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemReservationBarcodeBinding

internal class BarcodeAdapter(
    private val barcode: Bitmap
) : RecyclerView.Adapter<BarcodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarcodeViewHolder =
        BarcodeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: BarcodeViewHolder, position: Int) =
        holder.itemBind(barcode)

}

internal class BarcodeViewHolder(
    private val binding: ItemReservationBarcodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): BarcodeViewHolder {
            val binding =
                ItemReservationBarcodeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return BarcodeViewHolder(binding)
        }
    }

    fun itemBind(
        barcode: Bitmap
    ) {
        binding.setVariable(BR.barcode, barcode)
    }
}

