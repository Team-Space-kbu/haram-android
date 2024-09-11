package com.space.class_room.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.class_room.R

internal class ClassRoomAdapter : RecyclerView.Adapter<ClassRoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassRoomViewHolder =
        ClassRoomViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ClassRoomViewHolder, position: Int) {
    }
}

internal class ClassRoomViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): ClassRoomViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_classroom, parent, false)
            return ClassRoomViewHolder(view)
        }
    }

}

