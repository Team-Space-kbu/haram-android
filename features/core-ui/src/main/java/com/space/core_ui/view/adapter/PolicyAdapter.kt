package com.space.core_ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemPolicyBinding
import com.space.shared.data.core_ui.PolicyForm


class PolicyAdapter<T>(
    private val policies: List<PolicyForm<T>>,
    private val paramsItemHandler: PolicyHandler<T>
) : RecyclerView.Adapter<PolicyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PolicyViewHolder {
        return PolicyViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) =
        holder.itemBind(
            policies[position].policies,
            policies[position].title,
            policies[position].content,
            paramsItemHandler
        )

    override fun getItemCount(): Int = policies.size

    fun interface PolicyHandler<T> {
        fun onChecked(data: T, isChecked: Boolean)
    }
}

class PolicyViewHolder(
    private val binding: ItemPolicyBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): PolicyViewHolder {
            val binding =
                ItemPolicyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return PolicyViewHolder(binding)
        }
    }

    fun <T> itemBind(
        policy: T,
        title: String,
        content: String,
        paramsItemHandler: PolicyAdapter.PolicyHandler<T>
    ) {
        binding.setVariable(BR.policyText, content)
        binding.setVariable(BR.policyTitle, title)
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            paramsItemHandler.onChecked(policy, isChecked)
        }
    }

}