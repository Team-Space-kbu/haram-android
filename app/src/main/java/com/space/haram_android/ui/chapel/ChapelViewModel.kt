package com.space.haram_android.ui.chapel

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.intranet.ChapelInfoRes
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.repository.chapel.ChapelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("NullSafeMutableLiveData")
@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelRepository: ChapelRepository
) : ViewModel() {

    private val _chapelInfo = MutableLiveData<ChapelInfoRes>()
    val chapelInfo: LiveData<ChapelInfoRes> = _chapelInfo

    private val _chapelList = MutableLiveData<List<ChapelListRes>>()
    val chapelList: LiveData<List<ChapelListRes>> = _chapelList

    init {
        viewModelScope.launch {
            chapelRepository.getChapelInfo(chapelRepository.getIntranetTokenData()).let {
                when (it) {
                    is ResultData.Success<ChapelInfoRes> -> _chapelInfo.value = it.body
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