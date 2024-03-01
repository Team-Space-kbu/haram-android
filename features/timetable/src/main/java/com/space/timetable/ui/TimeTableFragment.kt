package com.space.timetable.ui


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.timetable.R
import com.space.core_ui.base.BaseFragment
import com.space.shared.UiStatusType
import com.space.shared.data.auth.AuthType
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
        viewModel.data.observe(this) {
            if (it.uiUiStatusType == UiStatusType.REJECT) {
                viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                activity?.finish()
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "시간표")
        binding.table.initTable(viewModel.day)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.data.observe(viewLifecycleOwner) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data!!
                val entities = data.mapIndexed { index, entity ->
                    val className = entity.subject
                    val schedule = viewModel.scheduleColor
                    if (!schedule.containsKey(className)) {
                        schedule[className] = viewModel.colorList.shuffled().first()
                    }
                    entity.toScheduleEntity(index, entity, schedule[className]!!)
                }.toList()
                binding.table.updateSchedules(ArrayList(entities))
            }
        }
    }
}
