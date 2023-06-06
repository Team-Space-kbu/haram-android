package com.space.haram_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.response.HomeRes
import com.space.haram_android.repository.home.HomeRepository
import com.space.haram_android.repository.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _homeForm = MutableLiveData<HomeRes>()
    val homeInfo: LiveData<HomeRes> = _homeForm

    private val _loginStatus = MutableLiveData<Boolean>(true)
    val loginStatus: LiveData<Boolean> = _loginStatus

    init {
        viewModelScope.launch {
            val res = homeRepository.getHome()
            when (res.code()) {
                200 -> if (res.code() == 200 && res.body()!!.code == "TK03") {
                    _loginStatus.value = false
                } else {
                    _homeForm.value = res.body()?.data!!
                }
                403 -> {
                    _loginStatus.value = false
                }
            }
        }
    }


}