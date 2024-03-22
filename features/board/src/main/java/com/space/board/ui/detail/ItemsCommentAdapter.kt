package com.space.board.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.board.BR
import com.space.board.databinding.ItemDetailCommentBinding
import com.space.shared.data.board.BoardComment
import timber.log.Timber

internal class ItemsCommentAdapter(
    private val boardComments: ArrayList<BoardComment>
) : RecyclerView.Adapter<ItemsCommentViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addComment(boardComments: List<BoardComment>){
        this.boardComments.clear()
        this.boardComments.addAll(boardComments)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsCommentViewHolder =
        ItemsCommentViewHolder.newInstance(parent)

    override fun getItemCount() = boardComments.size

    override fun onBindViewHolder(holder: ItemsCommentViewHolder, position: Int) {
        holder.bindItem(boardComments[position])
    }
}

internal class ItemsCommentViewHolder(
    private val binding: ItemDetailCommentBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ItemsCommentViewHolder {
            val binding =
                ItemDetailCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ItemsCommentViewHolder(binding)
        }
    }

    fun bindItem(boardComment: BoardComment) {
        binding.setVariable(BR.comment, boardComment)
        binding.executePendingBindings()
    }
}



