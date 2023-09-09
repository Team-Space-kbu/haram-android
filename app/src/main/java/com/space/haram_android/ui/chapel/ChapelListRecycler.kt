package com.space.haram_android.ui.chapel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.data.res.intranet.ChapelListRes
import com.space.haram_android.databinding.ModelChapelListLayoutBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChapelListRecycler : RecyclerView.Adapter<ChapelListViewHolder>() {
    var listRes: ArrayList<ChapelListRes> = ArrayList()

    fun addItem(chapelListRes: ChapelListRes) {
        listRes.add(chapelListRes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapelListViewHolder =
        ChapelListViewHolder(
            ModelChapelListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = listRes.size

    override fun onBindViewHolder(holder: ChapelListViewHolder, position: Int) =
        holder.bindItem(listRes[position])
}

class ChapelListViewHolder(
    private val binding: ModelChapelListLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindItem(chapelListRes: ChapelListRes) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val localDateTime = LocalDateTime.parse(chapelListRes.attendance, formatter)

        binding.chapelListTitle.text = chapelListRes.attendanceDays
        binding.chapelListNum.text =
            "${localDateTime.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분"))},  요일 :${chapelListRes.weekDays}"
    }
}