package com.space.core_ui.binding.adapter.view

import android.annotation.SuppressLint
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemPolicyBinding
import com.space.shared.data.core_ui.PolicyForm


class PolicyAdapter<T>(
    private var policies: List<PolicyForm<T>>,
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

    @SuppressLint("ClickableViewAccessibility")
    fun <T> itemBind(
        policy: T,
        title: String,
        content: String,
        paramsItemHandler: PolicyAdapter.PolicyHandler<T>
    ) {
//        binding.setVariable(BR.policyText, content)
        binding.setVariable(BR.policyTitle, title)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.text.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }
        binding.text.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            paramsItemHandler.onChecked(policy, isChecked)
        }
        binding.text.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.scroll.requestDisallowInterceptTouchEvent(true)
                    false
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    binding.scroll.requestDisallowInterceptTouchEvent(false)
                    false
                }
                else -> false
            }
        }

    }

}