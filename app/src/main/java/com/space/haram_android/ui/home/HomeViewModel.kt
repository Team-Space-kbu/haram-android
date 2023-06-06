package com.space.haram_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.HomeRes
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.repository.home.HomeRepository
import com.space.haram_android.repository.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository, private val authRepository: AuthRepository
) : ViewModel() {

    private val _homeForm: MutableLiveData<HomeRes?> = MutableLiveData<HomeRes?>()
    val homeInfo: LiveData<HomeRes?> = _homeForm

    private val _loginStatus = MutableLiveData<Boolean>(true)
    val loginStatus: LiveData<Boolean> = _loginStatus

    init {
        viewModelScope.launch {
            homeRepository.getHome().let { result ->
                when (result) {
                    is ResultData.Success<ResponseBody<HomeRes>> ->
                        _homeForm.value = result.body.data

                    is ResultData.Error -> _loginStatus.value = false

                    else -> _loginStatus.value = false
                }
            }

        }
    }


}