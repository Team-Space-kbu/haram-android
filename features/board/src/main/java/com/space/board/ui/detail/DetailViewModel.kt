package com.space.board.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardCommentUseCase
import com.space.domain.usecase.board.BoardDetailUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.common.exception.board.AnonymousRegistrationNotAllowedException
import com.space.shared.common.exception.board.BoardAlreadyExistsException
import com.space.shared.common.exception.board.CannotWriteCommentException
import com.space.shared.common.exception.board.FileMoveFailedException
import com.space.shared.common.exception.board.InvalidAnonymityException
import com.space.shared.common.exception.board.InvalidContentException
import com.space.shared.common.exception.board.InvalidTitleException
import com.space.shared.common.exception.board.NoWritePermissionException
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
    private val boardCommentUseCase: BoardCommentUseCase
) : ViewModel() {

    private val _detail: MutableLiveData<BoardDetail> = MutableLiveData<BoardDetail>()
    val detail: LiveData<BoardDetail> = _detail

    private val _comment: MutableLiveData<List<BoardComment>> =
        MutableLiveData<List<BoardComment>>()
    val comment: LiveData<List<BoardComment>> = _comment

    val anonymous = MutableLiveData<Boolean>(false)
    val inputText = MutableLiveData<String>()

    val toastMessage = MutableLiveData<String>()

    @Inject
    lateinit var navigatorImage: NavigatorImage

    fun getDetail(detailNum: BoardDetailNum) {
        viewModelScope.launch {
            val detail = async { boardDetailUseCase(detailNum) }.await()
            detail.mapCatching(
                onSuccess = { boardDetail ->
                    _detail.value = boardDetail
                },
                onError = ::handleError
            )
        }
    }

    fun postComment(detailNum: BoardDetailNum) {
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