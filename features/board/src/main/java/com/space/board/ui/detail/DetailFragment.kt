package com.space.board.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.board.BR
import com.space.board.R
import com.space.board.databinding.FragmentBoardDetailContainerBinding
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.ImageSliderAdapter
import com.space.core_ui.extraNotNull
import com.space.core_ui.hideKeyboard
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentBoardDetailContainerBinding>(
    R.layout.fragment_board_detail_container
) {

    private val detail by extraNotNull<String>("detail")
        .map { it.decodeFromString<BoardDetailNum>() }

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private val commentAdapter = ItemsCommentAdapter(ArrayList())
    private val commentHandler = NonParamsItemHandler {
        viewModel.postComment(detail)
    }


    override fun init() {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        detail.let { viewModel.getDetail(it) }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        binding.setVariable(BR.title, "게시판")
        binding.setVariable(BR.inputText, viewModel.inputText)
        binding.setVariable(BR.setAnonymous, viewModel.anonymous)
        binding.setVariable(BR.isAnonymous, detail.writeableAnonymous)
        binding.setVariable(BR.commentHandler, commentHandler)
        binding.setVariable(BR.imageComment, requireContext().getDrawable(R.drawable.ic_send_24px))
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                commentHandler.onClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
    }


    override fun afterObserverListener() {
        viewModel.detail.observe(this) { detail ->
            val image = detail.files.map { it.fileUrl }.toList()
            val adapter = ConcatAdapter(
                DetailAdapter(detail),
                ImageSliderAdapter(image, image.isNotEmpty()) {
                    viewModel.navigatorImage.openView(requireContext(), it)
                },
                LineAdapter(),
                CommentAdapter(commentAdapter)
            )
            commentAdapter.addComment(detail.comments  ?: arrayListOf())
            binding.recyclerView.adapter = adapter
        }
        viewModel.comment.observe(this) { boardComments ->
            commentAdapter.addComment(boardComments)
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }

}