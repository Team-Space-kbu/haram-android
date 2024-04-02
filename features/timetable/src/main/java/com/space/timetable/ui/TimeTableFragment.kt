package com.space.timetable.ui


import androidx.fragment.app.viewModels
import com.space.timetable.R
import com.space.core_ui.base.BaseFragment
import com.space.shared.UiStatusType
import com.space.shared.type.AuthType
import com.space.timetable.BR
import com.space.timetable.databinding.FragmentTimetaibleBinding
import com.space.timetable.util.toScheduleEntity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TimeTableFragment : BaseFragment<FragmentTimetaibleBinding>(
    R.layout.fragment_timetaible
) {

    private val viewModel: TimeTableViewModel by viewModels()

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) {
            when(it.uiUiStatusType){
                UiStatusType.REJECT ->{
                    viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                    activity?.finish()
                }
                UiStatusType.LOGOUT->{
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }
                else->{

                }
            }

        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "시간표")
        binding.table.initTable(viewModel.day)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() = with(viewModel) {
        super.afterObserverListener()
        viewModel.view.observe(viewLifecycleOwner) { result ->
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
                        scheduleColor[className] = color
                    }
                    entity.toScheduleEntity(index, entity, scheduleColor[className]!!)
                }.toList()
                binding.table.updateSchedules(ArrayList(entities))
            }
        }
    }
}
