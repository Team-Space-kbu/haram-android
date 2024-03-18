package com.space.board.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.space.shared.data.board.BoardDetail
import com.space.shared.util.formatToDate

@SuppressLint("SetTextI18n")
@BindingAdapter("setDetailText")
fun setDetail(
    textView: TextView,
    boardDetail: BoardDetail
){
    textView.text = "${boardDetail.createdBy} | ${formatToDate(boardDetail.createdAt)}"
}