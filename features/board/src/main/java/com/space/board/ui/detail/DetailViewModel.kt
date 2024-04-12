package com.space.board.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.board.BoardCommentDeleteUseCase
import com.space.domain.board.BoardCommentUseCase
import com.space.domain.board.BoardDetailDeleteUseCase
import com.space.domain.board.BoardDetailUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.common.exception.board.AnonymousRegistrationNotAllowedException
import com.space.shared.common.exception.board.BoardAlreadyExistsException
import com.space.shared.common.exception.board.CannotWriteCommentException
import com.space.shared.common.exception.board.InvalidAnonymityException
import com.space.shared.common.exception.board.InvalidContentException
import com.space.shared.common.exception.board.NonexistentCategoryException
import com.space.shared.data.board.BoardComment
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.mapCatching
import com.space.shared.model.BoardCommentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val boardDetailUseCase: BoardDetailUseCase,
    private val boardCommentUseCase: BoardCommentUseCase,
    private val boardDetailDeleteUseCase: BoardDetailDeleteUseCase,
    private val boardCommentDeleteUseCase: BoardCommentDeleteUseCase
) : BaseViewModel<BoardDetail>() {

    private val _comment: MutableLiveData<List<BoardComment>> =
        MutableLiveData<List<BoardComment>>()
    val comment: LiveData<List<BoardComment>> = _comment

    private val _deleteStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val deleteStatus: LiveData<Boolean> = _deleteStatus

    val anonymous = MutableLiveData(false)
    val inputText = MutableLiveData<String>()
    val toastMessage = MutableLiveData<String>()

    @Inject
    lateinit var navigatorImage: NavigatorImage

    fun getDetail(
        detailNum: BoardDetailNum
    ) {
        viewModelScope.launch {
            val detail = async { boardDetailUseCase(detailNum) }.await()
            detail.mapCatching(
                onSuccess = { boardDetail ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, boardDetail)
                },
                onError = ::handleError
            )
        }
    }

    fun deleteDetail(
        detailNum: BoardDetailNum
    ) {
        viewModelScope.launch {
            val detail = async { boardDetailDeleteUseCase(detailNum) }.await()
            detail.mapCatching(
                onSuccess = { boardDetail ->
                    _deleteStatus.value = boardDetail
                },
                onError = ::handleError
            )
        }
    }

    fun postComment(
        detailNum: BoardDetailNum
    ) {
        viewModelScope.launch {
            val content = inputText.value
            Timber.i(content)
            if (content == null) {
                toastMessage.value = "내용을 입력해주세요"
                return@launch
            }
            val model = BoardCommentModel(content, anonymous.value!!)
            val result = async { boardCommentUseCase(Pair(detailNum, model)) }.await()
            result.mapCatching(
                onSuccess = { boardComments ->
                    inputText.value = ""
                    toastMessage.value = "댓글 작성이 완료되었습니다."
                    _comment.value = boardComments
                },
                onError = ::handleError
            )
        }
    }

    fun deleteComment(
        detailNum: BoardDetailNum,
        boardComment: BoardComment
    ) {
        viewModelScope.launch {
            val detail = async {
                boardCommentDeleteUseCase(
                    Pair(
                        detailNum,
                        boardComment.seq.toInt()
                    )
                )
            }.await()
            detail.mapCatching(
                onSuccess = { boardDetail ->
                    toastMessage.value = "댓글이 삭제되었습니다."
                    _comment.value = boardDetail
                },
                onError = ::handleError
            )
        }
    }

    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is InvalidContentException -> toastMessage.value = "내용 값이 올바르지 않습니다."
            is InvalidAnonymityException -> toastMessage.value = "익명 여부 값이 올바르지 않습니다."
            is NonexistentCategoryException -> toastMessage.value = "게시글 카테고리가 존재하지 않습니다."
            is CannotWriteCommentException -> toastMessage.value = "댓글을 쓸 수 없습니다."
            is AnonymousRegistrationNotAllowedException -> toastMessage.value = "익명 등록이 불가능합니다."
            is BoardAlreadyExistsException -> toastMessage.value = "데이터가 이미 존재합니다."
            is IllegalArgumentException -> toastMessage.value = "잘못된 입력값이 존재합니다."
            is UnknownHostException, is SocketTimeoutException ->
                toastMessage.value = "네트워크를 연결할 수 없습니다."

            else -> toastMessage.value = "알 수 없는 오류가 발생했습니다."

        }
    }
}