package com.space.core_ui.binding.adapter.view

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.DividerGrayLineDecoration
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.ViewUiHeaderBinding
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType

class ItemHeaderAdapter(
    private val title: String,
    private val titleSize: Float,
    private val adapter: RecyclerView.Adapter<*>,
    private val layoutType: LayoutType = LayoutType.VERTICAL,
    private val dividerType: DividerType = DividerType.Default,
    private val padding: Boolean = true
) : RecyclerView.Adapter<HeaderVerticalViewHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeaderVerticalViewHolder {
        return HeaderVerticalViewHolder.newInstance(parent)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: HeaderVerticalViewHolder, position: Int) {
        holder.itemBind(title, titleSize)
        holder.setAdapter(adapter, layoutType, dividerType, padding)
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


    fun itemBind(
        title: String,
        titleSize: Float
    ) {
        if (titleSize != 18f) {
            binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
        }
        binding.setVariable(BR.title, title)

    }

    fun setAdapter(
        adapter: RecyclerView.Adapter<*>,
        layoutType: LayoutType,
        dividerType: DividerType,
        padding: Boolean
    ) {
        binding.recyclerView.setItemAnimator(null)
        binding.recyclerView.setHasFixedSize(true)
        findLayoutType(layoutType, adapter)
        findDividerType(dividerType, padding)
        binding.recyclerView.adapter = adapter
    }


    private fun findDividerType(
        dividerType: DividerType,
        padding: Boolean
    ) =
        when (dividerType) {
            DividerType.GrayLine -> {
                binding.recyclerView.addItemDecoration(
                    DividerGrayLineDecoration(
                        itemView.context,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin),
                        padding
                    )
                )
            }

            DividerType.GrayFlexLine -> {
                binding.recyclerView.addItemDecoration(
                    FlexGrayLineDecoration(
                        itemView.context,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin),
                        padding
                    )
                )
            }

            DividerType.Default -> {
                binding.recyclerView.addItemDecoration(
                    PaddingItemDecoration(
                        itemView.context,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.screen_margin),
                        padding
                    )
                )
            }

            DividerType.DefaultMargin10 -> {
                binding.recyclerView.addItemDecoration(
                    PaddingItemDecoration(
                        itemView.context,
                        itemView.context.resources.getDimensionPixelSize(R.dimen.margin_10dp),
                        padding
                    )
                )
            }

            else -> {

            }
        }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun findLayoutType(type: LayoutType, adapter: RecyclerView.Adapter<*>) =
        when (type) {
            LayoutType.HORIZONTAL -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        RecyclerView.HORIZONTAL,
                        false
                    ).apply {
                        recycleChildrenOnDetach = true
                    }
            }

            LayoutType.VERTICAL -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        LinearLayoutManager.VERTICAL,
                        false
                    ).apply {
                        recycleChildrenOnDetach = true
                    }
            }

            LayoutType.GRID -> {
                binding.recyclerView.isNestedScrollingEnabled = false
                binding.recyclerView.layoutManager =
                    GridLayoutManager(
                        itemView.context,
                        adapter.itemCount,
                        RecyclerView.VERTICAL,
                        false
                    ).apply {
                        recycleChildrenOnDetach = true
                    }
            }

            LayoutType.FLEX -> {
                binding.recyclerView.isNestedScrollingEnabled = false
                binding.recyclerView.layoutManager =
                    FlexboxLayoutManager(itemView.context).apply {
                        justifyContent = JustifyContent.FLEX_START
                    }
            }

            else -> {
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(
                        binding.recyclerView.context,
                        RecyclerView.VERTICAL,
                        false
                    ).apply {
                        recycleChildrenOnDetach = true
                    }
            }
        }
}

