package com.space.haram_android.ui.chapel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResultData
import com.space.data.response.intranet.ChapelInfoReq
import com.space.data.response.intranet.ChapelListRes
import com.space.domain.usecase.chapel.ChapelRepository
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelRepository: ChapelRepository,
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
                chapelRepository.getChapelInfo(chapelRepository.getIntranetTokenData()).let {
                    when (it) {
                        is ResultData.Success<ChapelInfoReq> -> _chapelInfo.value = it.body
                        is ResultData.Error -> {
                            Log.d("ChapelViewModel", it.throwable.message.toString())
                        }

                        else -> {}
                    }
                }
                chapelRepository.getChapelList(chapelRepository.getIntranetTokenData()).let {
                    when (it) {
                        is ResultData.Success<List<ChapelListRes>> -> _chapelList.value = it.body
                        is ResultData.Error -> {
                            Log.d("ChapelViewModel", it.throwable.message.toString())
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}