package com.space.board.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.databinding.ItemBoardCommentBinding
import com.space.core_ui.binding.adapter.DividerItemDecoration
import com.space.core_ui.R

internal class CommentAdapter(
    private val adapter: ItemsCommentAdapter,
) : RecyclerView.Adapter<CommentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindItem(adapter)
    }
}

internal class CommentViewHolder(
    private val binding: ItemBoardCommentBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CommentViewHolder {
            val binding =
                ItemBoardCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CommentViewHolder(binding)
        }
    }

    fun bindItem(
        adapter: ItemsCommentAdapter
    ) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                itemView.context,
                R.drawable.vw_line_divider,
                5,
                5,
                1
            )
        )
    }
}



