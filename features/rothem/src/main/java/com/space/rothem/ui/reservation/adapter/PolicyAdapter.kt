package com.space.rothem.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.databinding.ItemPolicyBinding
import com.space.shared.data.rothem.RothemPolicy


class PolicyAdapter(
    private val policies: List<RothemPolicy>,
    private val paramsItemHandler: ParamsItemHandler<Int>
) : RecyclerView.Adapter<PolicyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PolicyViewHolder {
        return PolicyViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: PolicyViewHolder, position: Int) =
        holder.itemBind(policies[position], paramsItemHandler)

    override fun getItemCount(): Int = policies.size
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

    fun itemBind(
        rothemPolicy: RothemPolicy,
        paramsItemHandler: ParamsItemHandler<Int>
    ) {
        binding.setVariable(BR.policySeq, rothemPolicy.policySeq.toString())
        binding.setVariable(BR.policyText, rothemPolicy.content)
        binding.setVariable(BR.policyTitle, rothemPolicy.title)
        binding.setVariable(BR.policyHandler, paramsItemHandler)

    }

}