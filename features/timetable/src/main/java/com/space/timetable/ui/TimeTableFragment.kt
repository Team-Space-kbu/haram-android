package com.space.timetable.ui


import androidx.fragment.app.viewModels
import com.space.timetable.R
import com.space.core_ui.base.BaseFragment
import com.space.timetable.BR
import com.space.timetable.databinding.FragmentTimetaibleBinding
import com.space.timetable.util.toScheduleEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeTableFragment : BaseFragment<FragmentTimetaibleBinding>(
    R.layout.fragment_timetaible
) {

    private val viewModel: TimeTableViewModel by viewModels()
    private val scheduleColor = HashMap<String, String>()
    private val colorList = listOf("#83a3e4", "#e28b7b", "#9b87db", "#8bc88e", "#f0af72", "#90cfc1")


    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "시간표")
        binding.table.initTable(viewModel.day)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.timetable.observe(viewLifecycleOwner) { timetable ->
            val entities = timetable.mapIndexed { index, entity ->
                val className = entity.subject
                if (!scheduleColor.containsKey(className)) {
                    scheduleColor[className] = colorList.shuffled().first()
                }
                entity.toScheduleEntity(index, entity, scheduleColor[className]!!)
            }.toList()
            binding.table.updateSchedules(ArrayList(entities))
        }
    }

}
