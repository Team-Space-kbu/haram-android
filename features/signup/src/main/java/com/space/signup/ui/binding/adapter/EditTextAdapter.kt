package com.space.signup.ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.space.signup.BR
import com.space.signup.databinding.ItemEditTitleBinding

class EditTextAdapter(
    private val text: String,
    private val inputStatus: MutableLiveData<Boolean>
) : RecyclerView.Adapter<EditTextHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTextHeaderViewHolder {
        return EditTextHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditTextHeaderViewHolder, position: Int) =
        holder.itemBind(text, inputStatus)

    override fun getItemCount(): Int = 1

}

class EditTextHeaderViewHolder(
    private val binding: ItemEditTitleBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditTextHeaderViewHolder {
            val binding = ItemEditTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EditTextHeaderViewHolder(binding)
        }
    }

    fun itemBind(
        text: String,
        inputStatus: MutableLiveData<Boolean>
    ) {
        binding.setVariable(BR.titleEdit, text)
    }
}