package com.space.board.ui.wirte

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.board.BR
import com.space.board.R
import com.space.board.databinding.FragmentWriteBinding
import com.space.core_ui.EditType
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.EditTextAdapter
import com.space.core_ui.binding.adapter.EditTitleAdapter
import com.space.core_ui.binding.adapter.FuncAdapter
import com.space.shared.data.core_ui.Func
import dagger.hilt.android.AndroidEntryPoint

import com.space.core_ui.createFileFromUri
import com.space.core_ui.extraNotNull
import com.space.core_ui.getFileName
import com.space.core_ui.map
import com.space.core_ui.showToast

import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.decodeFromString
import timber.log.Timber


@AndroidEntryPoint
class WriteFragment : BaseFragment<FragmentWriteBinding>(
    R.layout.fragment_write
) {

    companion object {
        fun newInstance() = WriteFragment()
    }

    private val info by extraNotNull<String>("info")
        .map { it.decodeFromString<BoardCategory>() }
    private val viewModel: WriteViewModel by viewModels()
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data!!.let {
                    val name = requireContext().getFileName(it)
                    val imageFile = requireContext().createFileFromUri(name.toString(), it)
                    viewModel.postImage(imageFile!!)
                }
            }
        }

    override fun init() {
        viewModel.setInfo(info)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        binding.setVariable(BR.title, "게시글 작성")
        binding.setVariable(
            BR.exitHandler,
            NonParamsItemHandler { parentFragmentManager.popBackStack() })
        binding.setVariable(BR.writeHandler,
            NonParamsItemHandler {
                viewModel.postWrite()
            }
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ConcatAdapter(
            EditTitleAdapter("게시글 제목"),
            EditTextAdapter(viewModel.title, "제목을 입력해주세요", EditType.ID),
            EditTitleAdapter("내용"),
            EditTextAdapter(viewModel.text, "내용을 입력해주세요", EditType.MULTI_LINE, true)

        )
        binding.menuRecyclerview.adapter = ConcatAdapter(
            FuncAdapter(Func(requireContext().getDrawable(R.drawable.ic_job), "사진등록")) {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                pickImageLauncher.launch(gallery)
            },
            FuncAdapter(Func(requireContext().getDrawable(R.drawable.ic_anonymous), "익명성 등록")) {

            },
        )
    }


    override fun beforeObserverListener() {
        viewModel.uiStatus.observe(this) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                setFragmentResult("updateUi", bundleOf("event" to true))
                parentFragmentManager.popBackStack()
            }
        }
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
    }


    override fun afterObserverListener() {

    }

}