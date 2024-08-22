package com.space.board.ui.wirte

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.board.BR
import com.space.board.R
import com.space.board.databinding.FragmentWriteBinding
import com.space.core_ui.extension.EditType
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.item.input.EditTextAdapter
import com.space.core_ui.binding.adapter.item.input.EditTitleAdapter
import com.space.core_ui.binding.adapter.func.FuncAdapter
import com.space.core_ui.binding.adapter.func.FuncCheckAdapter
import com.space.shared.data.core_ui.Func
import dagger.hilt.android.AndroidEntryPoint

import com.space.core_ui.extension.createFileFromUri
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.getFileName
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast

import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.decodeFromString


@SuppressLint("UseCompatLoadingForDrawables")
@AndroidEntryPoint
class WriteFragment : BaseFragment<FragmentWriteBinding>(
    R.layout.fragment_write
) {

    companion object {
        fun newInstance() = WriteFragment()
    }

    private val viewModel: WriteViewModel by viewModels()
    private val imageAdapter = ImageAdapter(arrayListOf())
    private val info by extraNotNull<String>("info").map { it.decodeFromString<BoardCategory>() }
    private val inputMethodManager by lazy {
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    private val popStack = NonParamsItemHandler {
        parentFragmentManager.popBackStack()
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data!!.let {
                    imageAdapter.addItem(image = it.toString())
                    val name = requireContext().getFileName(it)
                    val imageFile = requireContext().createFileFromUri(name.toString(), it)
                    viewModel.postImage(imageFile!!)
                }
            }
        }
    private val menuAdapter by lazy {
        val anonymous = requireContext().getDrawable(R.drawable.ic_anonymous)
        val job = requireContext().getDrawable(R.drawable.ic_job)
        ConcatAdapter(
            FuncAdapter(Func(job, "사진등록")) {
                if ((imageAdapter.itemCount + 1) <= 8) {
                    pickImageLauncher.launch(
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                    )
                } else {
                    requireContext().showToast("사진은 최대 8개 등록할 수 있습니다.")
                }
            },
            FuncCheckAdapter(Func(anonymous, "익명성 등록"), viewModel.isAnonymous)
        )
    }


    override fun init() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        viewModel.setInfo(info)
    }

    override fun initView() {
        super.initView()
        val writeHandler = NonParamsItemHandler {
            viewModel.postWrite()
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
        binding.setVariable(BR.title, "게시글 작성")
        binding.setVariable(BR.exitHandler, popStack)
        binding.setVariable(BR.writeHandler, writeHandler)
        binding.recyclerView.adapter = ConcatAdapter(
            EditTitleAdapter("게시글 제목"),
            EditTextAdapter(viewModel.title, "제목을 입력해주세요", EditType.TEXT),
            EditTitleAdapter("내용"),
            EditTextAdapter(viewModel.text, "내용을 입력해주세요", EditType.MULTI_LINE, true),
            SelectImageAdapter(imageAdapter)
        )
        binding.menuRecyclerview.adapter = menuAdapter
    }

    override fun beforeObserverListener() {
        viewModel.uiStatus.observe(this) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                setFragmentResult("updateUi", bundleOf("event" to true))
                popStack.onClick()
            }
        }
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
    }

}