package com.space.biblemon.ui.intranet.chapel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.shared.result.ResultData
import com.space.shared.response.intranet.ChapelInfoReq
import com.space.shared.response.intranet.ChapelListRes
import com.space.domain.usecase.ChapelUseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelUseCase: ChapelUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _chapelInfo = MutableLiveData<ChapelInfoReq>()
    val chapelInfo: LiveData<ChapelInfoReq> = _chapelInfo

    private val _chapelList = MutableLiveData<List<ChapelListRes>>()
    val chapelList: LiveData<List<ChapelListRes>> = _chapelList

    init {
        viewModelScope.launch(ioDispatcher) {
            withContext(mainDispatcher){
                chapelUseCase.getChapelInfo(chapelUseCase.getIntranetTokenData()).let {
                    when (it) {
                        is ResultData.Success<ChapelInfoReq> -> _chapelInfo.value = it.body
                        is ResultData.Error -> {
                            Timber.d(it.throwable.message.toString())
                        }

                        else -> {}
                    }
                }
                chapelUseCase.getChapelList(chapelUseCase.getIntranetTokenData()).let {
                    when (it) {
                        is ResultData.Success<List<ChapelListRes>> -> _chapelList.value = it.body
                        is ResultData.Error -> {
                            Timber.d(it.throwable.message.toString())
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}