package com.space.other.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.other.databinding.ItemOtherSettingBinding
import com.space.shared.type.SettingType

internal class SettingAdapter(
    private val eventView: EventView
) : RecyclerView.Adapter<SettingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder =
        SettingViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) =
        holder.itemBind(eventView)

    fun interface EventView {
        fun settingType(settingType: SettingType)
    }
}

internal class SettingViewHolder(
    private val binding: ItemOtherSettingBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SettingViewHolder {
            val binding = ItemOtherSettingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SettingViewHolder(binding)
        }
    }

    fun itemBind(
        eventView: SettingAdapter.EventView
    ) {
        val adapter = ConcatAdapter(
            ItemCatalogAdapter("하람 서비스 약관") {
                eventView.settingType(SettingType.SPACE_POLICY)
            },
            ItemCatalogAdapter("개인정보처리방침") {
                eventView.settingType(SettingType.PRIVACY_POLICY)
            },
            ItemCatalogAdapter("오픈소스 라이센스") {
                eventView.settingType(SettingType.LICENSES)
            },
            ItemCatalogAdapter("로그아웃", Color.RED) {
                eventView.settingType(SettingType.LOGOUT)
            },
        )
        binding.recyclerView.adapter = adapter
    }
}

