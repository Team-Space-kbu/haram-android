package com.space.rothem.ui.reserved.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemReservationQrcodeImgBinding

internal class QrcodeAdapter(
    private val barcode: Bitmap
) : RecyclerView.Adapter<QrcodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrcodeViewHolder =
        QrcodeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: QrcodeViewHolder, position: Int) =
        holder.itemBind(barcode)

}

internal class QrcodeViewHolder(
    private val binding: ItemReservationQrcodeImgBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): QrcodeViewHolder {
            val binding =
                ItemReservationQrcodeImgBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return QrcodeViewHolder(binding)
        }
    }

    fun itemBind(
        barcode: Bitmap
    ) {
        binding.setVariable(BR.qrcode, barcode)
    }
}

