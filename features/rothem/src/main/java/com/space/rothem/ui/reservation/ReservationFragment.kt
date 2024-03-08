package com.space.rothem.ui.reservation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.rothem.ui.reservation.adapter.CalendarAdapter
import com.space.rothem.ui.reservation.adapter.PolicyAdapter
import com.space.rothem.ui.reservation.adapter.RoomsAdapter
import com.space.rothem.ui.reservation.adapter.SelectTimeAdapter
import com.space.rothem.ui.reservation.adapter.SelectTimeItemAdapter
import com.space.rothem.ui.reservation.adapter.TimeAdapter
import com.space.shared.data.rothem.RothemTime
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ReservationFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {
    companion object {
        fun newInstance() = ReservationFragment()
    }

    private val viewModel: ReservationViewModel by viewModels()
    private val roomSeq by extraNotNull<String>("reservation")
        .map { encodeString ->
            encodeString
        }
    private val selectHandler = ParamsItemHandler<RothemTime> { time ->
        viewModel.dataList.value?.let {
            if (it.containsKey(time.timeSeq)) {
                Timber.d("remove")
                viewModel.removeData(time.timeSeq)
            } else {
                if (it.size >= 2) {
                    Timber.d("stay")
                    Toast.makeText(requireContext(), "두개 이상 선택할 수 없습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Timber.d("add")
                    viewModel.updateData(time)
                }
            }
        }

    }
    private val amTimeItemAdapter by lazy {
        SelectTimeItemAdapter(arrayListOf(), viewModel.dataList, selectHandler)
    }
    private val pmTimeItemAdapter by lazy {
        SelectTimeItemAdapter(arrayListOf(), viewModel.dataList, selectHandler)
    }
    private val timeAdapter by lazy {
        ConcatAdapter(
            SelectTimeAdapter("오전", amTimeItemAdapter),
            SelectTimeAdapter("오후", pmTimeItemAdapter)
        )
    }

    override fun init() {
        roomSeq.let {
            viewModel.getReservationInfo(roomSeq)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, "예약하기")
        binding.lifecycleOwner = viewLifecycleOwner
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        viewModel.rothem.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                RoomsAdapter(it.roomResponse),
                PolicyAdapter(it.policyResponses) {

                },
                CalendarAdapter(it.calendarResponses, viewModel.selectCalender),
                TimeAdapter(timeAdapter)
            )
            binding.recyclerView.adapter = adapter
        }
        viewModel.selectCalender.observe(viewLifecycleOwner) { calender ->
            amTimeItemAdapter.setList(calender.times.filter { it.meridiem == "am" })
            pmTimeItemAdapter.setList(calender.times.filter { it.meridiem == "pm" })
            timeAdapter.notifyDataSetChanged()
        }
    }


}