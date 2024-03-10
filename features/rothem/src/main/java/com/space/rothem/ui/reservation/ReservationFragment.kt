package com.space.rothem.ui.reservation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.EditType
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.view.adapter.EditTextAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.clearBackStack
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.view.adapter.ButtonAdapter
import com.space.rothem.ui.reservation.adapter.CalendarAdapter
import com.space.rothem.ui.reservation.adapter.InputInfoAdapter
import com.space.core_ui.view.adapter.PolicyAdapter
import com.space.rothem.ui.home.RothemFragment
import com.space.rothem.ui.reservation.adapter.RoomsAdapter
import com.space.rothem.ui.reservation.adapter.SelectTimeAdapter
import com.space.rothem.ui.reservation.adapter.SelectTimeItemAdapter
import com.space.rothem.ui.reservation.adapter.TimeAdapter
import com.space.shared.data.core_ui.PolicyForm
import com.space.shared.data.rothem.ReservationStatus
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
        try {
            val dataList = viewModel.dataList.value ?: return@ParamsItemHandler
            if (dataList.containsKey(time.timeSeq)) {
                viewModel.removeData(time.timeSeq)
                return@ParamsItemHandler
            }
            if (dataList.size >= 2) {
                Toast.makeText(requireContext(), "1시간 이상 선택할 수 없습니다.", Toast.LENGTH_LONG).show()
                return@ParamsItemHandler
            }
            val selectKey = dataList.keys.first()
            if (viewModel.isValidTimeSlot(selectKey, time)) {
                viewModel.updateData(time)
            } else {
                Toast.makeText(requireContext(), "연속된 시간만 예약할 수 있습니다.", Toast.LENGTH_LONG).show()
            }
        } catch (e: NoSuchElementException) {
            Timber.d("key index 0!!")
            viewModel.updateData(time)
        } catch (e: Exception) {
            Timber.i(e.message)
            Toast.makeText(requireContext(), "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
        }
    }
    private lateinit var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private val amTimeItemAdapter by lazy {
        SelectTimeItemAdapter(arrayListOf(), viewModel.dataList, selectHandler)
    }
    private val pmTimeItemAdapter by lazy {
        SelectTimeItemAdapter(arrayListOf(), viewModel.dataList, selectHandler)
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

    override fun beforeObserverListener() {
        viewModel.rothem.observe(this) { reservation ->
            val policy = reservation.policyResponses.map { PolicyForm(it, it.title, it.content) }
            adapter = ConcatAdapter(
                RoomsAdapter(reservation.roomResponse),
                PolicyAdapter(policy) { rothem, isChecked ->
                    viewModel.setPolicy(rothem, isChecked)
                },
                CalendarAdapter(reservation.calendarResponses, viewModel.selectCalender),
                TimeAdapter(
                    ConcatAdapter(
                        SelectTimeAdapter("오전", amTimeItemAdapter),
                        SelectTimeAdapter("오후", pmTimeItemAdapter)
                    )
                ),
                InputInfoAdapter(
                    ConcatAdapter(
                        EditTextAdapter(viewModel.nameLive, "이름", EditType.NAME),
                        EditTextAdapter(viewModel.cellPhone, "핸드폰", EditType.PHONE)
                    )
                ),
                ButtonAdapter("예약하기") {
                    viewModel.requestReservation(roomSeq)
                }
            )
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        viewModel.rothem.observe(viewLifecycleOwner) {
            if (::adapter.isInitialized) {
                binding.recyclerView.adapter = adapter
            }
        }
        viewModel.selectCalender.observe(viewLifecycleOwner) { calender ->
            viewModel.dataList.value?.clear()
            amTimeItemAdapter.setList(calender.times.filter { it.meridiem == "am" })
            pmTimeItemAdapter.setList(calender.times.filter { it.meridiem == "pm" })
        }
        viewModel.request.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.text, Toast.LENGTH_LONG).show()
            if (it == ReservationStatus.PASS){
                parentFragmentManager.popBackStack(RothemFragment::class.java.javaClass.name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                parentFragmentManager.clearBackStack()
            }
        }
    }


}