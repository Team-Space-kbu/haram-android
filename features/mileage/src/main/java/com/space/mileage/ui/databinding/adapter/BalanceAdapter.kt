package com.space.mileage.ui.databinding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.space.mileage.R
import java.text.DecimalFormat

internal class MileageBalanceAdapter(
    private val string: String
) : RecyclerView.Adapter<MileageBalanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileageBalanceViewHolder =
        MileageBalanceViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: MileageBalanceViewHolder, position: Int) =
        holder.bindItem(string)


}

internal class MileageBalanceViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): MileageBalanceViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_mileage_balance, parent, false)
            return MileageBalanceViewHolder(view)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bindItem(string: String) {
        itemView.findViewById<TextView>(R.id.balance_text).text = "${string}원"
    }
}

