package com.space.rothem.ui.reserved

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.R
import com.space.core_ui.extension.EditType
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.binding.adapter.item.EditTextAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.extension.clearBackStack
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.core_ui.binding.adapter.item.ButtonAdapter
import com.space.core_ui.binding.adapter.item.PolicyAdapter
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.rothem.ui.home.RothemFragment
import com.space.rothem.ui.reserved.adapter.CalendarItemAdapter
import com.space.rothem.ui.reserved.adapter.RoomsAdapter
import com.space.rothem.ui.reserved.adapter.SelectTimeItemAdapter
import com.space.rothem.ui.reserved.adapter.ShimmerReservedAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.core_ui.PolicyForm
import com.space.shared.data.rothem.ReservationStatus
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.RothemTime
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ReservedFragment : ContainerFragment<RoomReservation>() {
    companion object {
        fun newInstance() = ReservedFragment()
    }

    override val viewModel: ReservedViewModel by viewModels()
    override val viewTitle: String = "예약하기"

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
                requireContext().showToast("1시간 이상 선택할 수 없습니다.")
                return@ParamsItemHandler
            }
            val selectKey = dataList.keys.first()
            if (viewModel.isValidTimeSlot(selectKey, time)) {
                viewModel.updateData(time)
            } else {
                requireContext().showToast("연속된 시간만 예약할 수 있습니다.")
            }
        } catch (e: NoSuchElementException) {
            Timber.d("key index 0!!")
            viewModel.updateData(time)
        } catch (e: Exception) {
            Timber.i(e.message)
            requireContext().showToast("알 수 없는 오류가 발생했습니다.")
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
        viewModel.getReservationInfo(roomSeq)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerReservedAdapter()
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { reservation ->
            val data = reservation.data ?: return@observe
            val policy = data.policyResponses.map { PolicyForm(it, it.title, it.content) }
            adapter = ConcatAdapter(
                RoomsAdapter(data.roomResponse),
                PolicyAdapter(policy) { rothem, isChecked ->
                    viewModel.setPolicy(rothem, isChecked)
                },
                ItemHeaderAdapter(
                    title = "날짜선택",
                    titleSize = 18f,
                    adapter = CalendarItemAdapter(data.calendarResponses, viewModel.selectCalender),
                    layoutType = LayoutType.GRID,
                    padding = false
                ),
                ItemHeaderAdapter(
                    "시간선택",
                    18f,
                    ConcatAdapter(
                        ItemHeaderAdapter(
                            "오전",
                            14f,
                            amTimeItemAdapter,
                            layoutType = LayoutType.FLEX,
                            dividerType = DividerType.None
                        ),
                        ItemHeaderAdapter(
                            "오후",
                            14f,
                            pmTimeItemAdapter,
                            layoutType = LayoutType.FLEX,
                            dividerType = DividerType.None
                        )
                    ),
                    padding = false
                ),
                ItemHeaderAdapter(
                    "예약자정보",
                    18f,
                    ConcatAdapter(
                        EditTextAdapter(viewModel.nameLive, "이름", EditType.NAME),
                        EditTextAdapter(
                            string = viewModel.cellPhone,
                            hintText = "핸드폰",
                            inputType = EditType.PHONE,
                            editorAction = true
                        )
                    ),
                    dividerType = DividerType.None
                ),
                ButtonAdapter("예약하기") {
                    viewModel.requestReservation(roomSeq)
                }
            )
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun afterObserverListener() {
        viewModel.view.observe(viewLifecycleOwner) {
            if (::adapter.isInitialized && it.uiUiStatusType == UiStatusType.SUCCESS) {
                binding.recyclerView.adapter = adapter
            }
        }
        viewModel.selectCalender.observe(viewLifecycleOwner) { calender ->
            viewModel.dataList.value?.clear()
            amTimeItemAdapter.setList(calender.times.filter { it.meridiem == "am" })
            pmTimeItemAdapter.setList(calender.times.filter { it.meridiem == "pm" })
        }
        viewModel.request.observe(viewLifecycleOwner) {
            requireContext().showToast(it.text)
            if (it == ReservationStatus.PASS) {
                setFragmentResult("updateUi", bundleOf("event" to true))
                parentFragmentManager.popBackStack(
                    RothemFragment::class.java.javaClass.name,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                );
                parentFragmentManager.clearBackStack()
            }
        }
    }


}