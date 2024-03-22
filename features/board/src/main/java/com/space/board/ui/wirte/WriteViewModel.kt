package com.space.board.ui.wirte

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardPostUseCase
import com.space.domain.usecase.image.ImageUseCase
import com.space.shared.ImageType
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.common.exception.board.AnonymousRegistrationNotAllowedException
import com.space.shared.common.exception.board.BoardAlreadyExistsException
import com.space.shared.common.exception.board.FileMoveFailedException
import com.space.shared.common.exception.board.InvalidAnonymityException
import com.space.shared.common.exception.board.InvalidTitleException
import com.space.shared.common.exception.board.NoWritePermissionException
import com.space.shared.common.exception.board.NonexistentCategoryException
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.image.StorageImage
import com.space.shared.mapCatching
import com.space.shared.model.BoardModel
import com.space.shared.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val imageUseCase: ImageUseCase,
    private val boardPostUseCase: BoardPostUseCase
) : ViewModel() {

    val title = MutableLiveData<String>()
    val text = MutableLiveData<String>()
    val isAnonymous = MutableLiveData<Boolean>(false)

    private val image: ArrayList<StorageImage> = arrayListOf()
    private lateinit var info: BoardCategory

    private val _uiStatus = MutableLiveData<UiStatus<Boolean>>()
    val uiStatus: LiveData<UiStatus<Boolean>> = _uiStatus

    val toastMessage = MutableLiveData<String>()

    fun setInfo(info: BoardCategory) {
        this.info = info
    }

    fun postImage(file: File) {
        viewModelScope.launch {
            val result = async { imageUseCase(Pair(file, ImageType.BOARD)) }.await()
            result.mapCatching(
                onSuccess = {
                    Timber.i(it.toString())
                    image.add(it)
                },
                onError = ::handleError
            )
        }
    }

    fun postWrite() {
        viewModelScope.launch {
            val title = title.value?: return@launch
            val text = text.value?: return@launch
            if (title.isEmpty()) {
                toastMessage.value = "제목이 비어 있습니다."
                return@launch
            }
            if (text.isEmpty()) {
                toastMessage.value = "내용이 비어 있습니다."
                return@launch
            }
            val anonymousType =
                if (info.writeableAnonymous) {
                    isAnonymous.value
                } else {
                    false
                }
            val list = image.mapIndexed { index, storageImage ->
                ImageModel(
                    storageImage.tempFilePath,
                    storageImage.fileName,
                    storageImage.fileExt,
                    storageImage.fileSize,
                    index + 1
                )
            }.toList()
            val model = BoardModel(title, text, list, anonymousType!!)
            val result = async { boardPostUseCase(Pair(info.categorySeq, model)) }.await()
            result.mapCatching(
                onSuccess = {
                    toastMessage.value = "작성이 완료되었습니다."
                    _uiStatus.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::handleError
            )
        }
    }


    private fun handleError(throwable: Throwable) {
        Timber.i(throwable.message)
        when (throwable) {
            is InvalidTitleException -> toastMessage.value = "제목 값이 올바르지 않습니다."
            is InvalidAnonymityException -> toastMessage.value = "익명 여부 값이 올바르지 않습니다."
            is NonexistentCategoryException -> toastMessage.value = "게시글 카테고리가 존재하지 않습니다."
            is NoWritePermissionException -> toastMessage.value = "글을 쓸 권한이 없습니다."
            is AnonymousRegistrationNotAllowedException -> toastMessage.value = "익명 등록이 불가능합니다."
            is BoardAlreadyExistsException -> toastMessage.value = "Board 가 이미 존재합니다."
            is FileMoveFailedException -> toastMessage.value = "파일을 옮기는데 실패했습니다."
            is UnknownHostException, is SocketTimeoutException ->
                toastMessage.value = "네트워크를 연결할 수 없습니다."

            else -> toastMessage.value = "알 수 없는 오류가 발생했습니다."

        }
    }

}