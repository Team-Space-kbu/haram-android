package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.response.home.HomeInfo
import com.space.data.success
import com.space.domain.usecase.HomeUseCase
//import com.space.domain2.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _homeInfo = MutableLiveData<HomeInfo>()
    val homeInfo: LiveData<HomeInfo> = _homeInfo

    init {
        viewModelScope.launch {
            val homeInfo = async { homeUseCase() }
            _homeInfo.value = homeInfo.await().success()
        }
    }


}