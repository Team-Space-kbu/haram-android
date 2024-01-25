package com.space.timetable.ui


import androidx.fragment.app.viewModels
import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleEntity
import com.space.timetable.R
import com.space.core_ui.base.BaseFragment
import com.space.timetable.BR
import com.space.timetable.databinding.FragmentTimetaibleBinding
import com.space.timetable.util.toDay
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        val day = arrayOf("월", "화", "수", "목", "금")
        binding.table.initTable(day)
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
                ScheduleEntity(
                    index,                              //originId
                    className,                     //scheduleName
                    entity.classRoomLocation,           //roomInfo
                    entity.toDay(entity),               //ScheduleDay object (MONDAY ~ SUNDAY)
                    entity.startTime,                    //startTime format: "HH:mm"
                    entity.endTime,                     //endTime  format: "HH:mm"
                    scheduleColor[className]!!,    //backgroundColor (optional)
                    "#FFFFFF"
                )
            }.toMutableList()
            binding.table.updateSchedules(ArrayList(entities))
        }
    }

}