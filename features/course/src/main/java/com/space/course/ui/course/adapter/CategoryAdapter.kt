package com.space.course.ui.course.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.databinding.ItemCategoryBinding
import com.space.course.util.toCategory
import com.space.shared.data.course.Course

internal class CategoryAdapter(
    private val courses: MutableList<Course>,
    private val itemHandler: ParamsItemHandler<Course>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(details: List<Course>) {
        courses.addAll(details)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder.newInstance(parent)


    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bindItem(courses[position], itemHandler)


}

internal class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CategoryViewHolder {
            val binding =
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }
    }

    fun bindItem(course: Course, itemHandler: ParamsItemHandler<Course>) {
        binding.setVariable(BR.category, course.toCategory())
        val tag: MutableList<String> = ArrayList<String>().apply {
            course.classRoomName?.let {
                if (it.isNotEmpty()) {
                    add(it)
                }
            }
            course.lectureNum?.let {
                if (it.isNotEmpty()) {
                    add(it)
                }
            }
            course.lectureDay?.let {
                if (it.isNotEmpty()) {
                    add(it)
                }
            }
        }

        binding.recyclerView.adapter = CategoryTagAdapter(tag)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        binding.category.setOnClickListener {
            itemHandler.onClick(course)
        }
    }
}


