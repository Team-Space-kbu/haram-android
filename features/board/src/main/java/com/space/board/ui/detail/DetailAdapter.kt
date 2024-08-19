package com.space.board.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.databinding.ItemDetailMainTextBinding
import com.space.board.BR
import com.space.core_ui.util.NonParamsItemHandler
import com.space.shared.data.board.BoardDetail

internal class DetailAdapter(
    private val boardDetail: BoardDetail,
    private val paramsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<DetailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder.newInstance(parent)

    override fun getItemCount() = 1


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindItem(boardDetail, paramsItemHandler)
    }
}

internal class DetailViewHolder(
    private val binding: ItemDetailMainTextBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): DetailViewHolder {
            val binding =
                ItemDetailMainTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return DetailViewHolder(binding)
        }
    }

    fun bindItem(
        boardDetail: BoardDetail,
        paramsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.content, boardDetail)
        binding.setVariable(BR.contentHandler, paramsItemHandler)
    }
}



