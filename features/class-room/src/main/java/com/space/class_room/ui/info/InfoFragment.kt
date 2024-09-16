package com.space.class_room.ui.info

import androidx.fragment.app.viewModels
import com.space.class_room.BR
import com.space.class_room.R
import com.space.class_room.databinding.FragmentClassroomBinding
import com.space.class_room.util.toScheduleEntity
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.shared.UiStatusType
import com.space.shared.data.course.Course
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : ContainerCustomFragment<FragmentClassroomBinding, List<Course>>(
    R.layout.fragment_classroom
){
    private val text by extraNotNull<String>("info").map { it }

    companion object {
        fun newInstance() = InfoFragment()
    }

    override val viewModel: InfoViewModel by viewModels()

    override fun initView() {
        super.initView()
        viewModel.setInit(text)
        binding.setVariable(BR.title, text)
        binding.table.initTable(viewModel.day)
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        view.observe(viewLifecycleOwner) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data!!
                val entities = data.mapIndexed { index, entity ->
                    val className = entity.subject
                    if (!scheduleColor.containsKey(className)) {
                        var color: String
                        var colorIndex = 0
                        do {
                            color = colorList.shuffled().first()
                            colorIndex += 1
                            if (colorIndex > 30) {
                                break
                            }
                        } while (scheduleColor.containsValue(color))
                        scheduleColor[className?: "정보없음"] = color
                    }
                    entity.toScheduleEntity(index, scheduleColor[className]!!)
                }.toList()
                binding.table.updateSchedules(ArrayList(entities))
            }
        }
    }
}