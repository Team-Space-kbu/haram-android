package com.space.core_ui.binding.adapter.view

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.DividerGrayLineDecoration
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.ViewUiHeaderBinding
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType

class UiHeaderAdapter(
    private val title: String,
    private val titleSize: Float,
    private val adapter: RecyclerView.Adapter<*>,
    private val layoutType: LayoutType = LayoutType.VERTICAL,
    private val dividerType: DividerType = DividerType.NONE
) : RecyclerView.Adapter<HeaderVerticalViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderVerticalViewHolder =
        HeaderVerticalViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderVerticalViewHolder, position: Int) {
        holder.itemBind(title, titleSize)
        holder.adapterBind(adapter, layoutType, dividerType)
    }

}

class HeaderVerticalViewHolder(
    private val binding: ViewUiHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): HeaderVerticalViewHolder {
            val binding = ViewUiHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HeaderVerticalViewHolder(binding)
        }
    }

    private fun findDividerType(dividerType: DividerType) =
        when (dividerType) {
            DividerType.GrayLine -> {
                binding.recyclerView.addItemDecoration(
                    DividerGrayLineDecoration(
                        itemView.context,
                        R.drawable.vw_line_divider,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin)
                    )
                )
            }

            DividerType.GrayFlexLine -> {
                binding.recyclerView.addItemDecoration(
                    FlexGrayLineDecoration(
                        itemView.context,
                        R.drawable.vw_line_flex_divider,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin)
                    )
                )
            }

            DividerType.Default -> {
                binding.recyclerView.addItemDecoration(
                    PaddingItemDecoration(
                        itemView.context,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin)
                    )
                )
            }

            else -> {

            }
        }

    private fun findLayoutType(type: LayoutType) =
        when (type) {
            LayoutType.HORIZONTAL -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        RecyclerView.HORIZONTAL,
                        false
                    )
            }

            LayoutType.VERTICAL -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }

            else -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        RecyclerView.VERTICAL,
                        false
                    )
            }
        }

    fun adapterBind(
        adapter: RecyclerView.Adapter<*>,
        layoutType: LayoutType,
        dividerType: DividerType
    ) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        findLayoutType(layoutType)
        findDividerType(dividerType)
    }

    fun itemBind(
        title: String,
        titleSize: Float,
    ) {
        binding.setVariable(BR.title, title)
        binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)

    }
}

