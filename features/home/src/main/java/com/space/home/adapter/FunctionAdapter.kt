package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.home.R

internal class FunctionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FunctionViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}

internal class FunctionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun newInstance(parent: ViewGroup): FunctionViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_info_function, parent, false)
            return FunctionViewHolder(view)
        }
    }
}