package com.space.other.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.NonParamsItemHandler
import com.space.other.BR
import com.space.other.databinding.ItemOtherUserBinding
import com.space.shared.data.auth.User

internal class UserAdapter(
    private val user: User,
    private val nonParamsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.itemBind(user, nonParamsItemHandler)

}

internal class UserViewHolder(
    private val binding: ItemOtherUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): UserViewHolder {
            val binding = ItemOtherUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return UserViewHolder(binding)
        }
    }

    fun itemBind(
        user: User,
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.user, user)
        binding.other.setOnClickListener {
            nonParamsItemHandler.onClick()
        }
    }
}

