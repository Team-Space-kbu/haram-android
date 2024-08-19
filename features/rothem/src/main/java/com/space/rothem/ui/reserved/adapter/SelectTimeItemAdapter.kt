package com.space.rothem.ui.reserved.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.ParamsItemHandler
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemTimeSelectBinding
import com.space.shared.data.rothem.RothemTime

internal class SelectTimeItemAdapter(
    private val times: ArrayList<RothemTime>,
    private val dataList: LiveData<MutableMap<Int, RothemTime>>,
    private val paramsItemHandler: ParamsItemHandler<RothemTime>
) : RecyclerView.Adapter<SelectTimeItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(rothemTimes: List<RothemTime>) {
        times.clear()
        times.addAll(rothemTimes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeItemViewHolder =
        SelectTimeItemViewHolder.newInstance(parent)

    override fun getItemCount() = if (times.isEmpty()) 0 else times.size

    override fun onBindViewHolder(holder: SelectTimeItemViewHolder, position: Int) =
        holder.itemBind(
            times[position],
            dataList,
            paramsItemHandler
        )

}

internal class SelectTimeItemViewHolder(
    private val binding: ItemRothemTimeSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectTimeItemViewHolder {
            val binding =
                ItemRothemTimeSelectBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return SelectTimeItemViewHolder(binding)
        }
    }

    init {
        itemView.doOnAttach {
            binding.lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            binding.lifecycleOwner = null
        }
    }


    fun itemBind(
        time: RothemTime,
        dataList: LiveData<MutableMap<Int, RothemTime>>,
        paramsItemHandler: ParamsItemHandler<RothemTime>
    ) {
        binding.setVariable(BR.time, time)
        binding.setVariable(BR.data, dataList)
        if (!time.isReserved) {
            binding.time.setOnClickListener {
                paramsItemHandler.onClick(time)
            }
        }
    }
}

