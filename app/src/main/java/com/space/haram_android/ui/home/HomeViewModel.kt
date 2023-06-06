package com.space.haram_android.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.haram_android.common.data.response.HomeRes
import com.space.haram_android.repository.home.HomeRepository
import com.space.haram_android.repository.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _homeForm: MutableLiveData<HomeRes> = MutableLiveData<HomeRes>()
    val homeInfo: LiveData<HomeRes> = _homeForm

    private val _loginStatus = MutableLiveData<Boolean>(true)
    val loginStatus: LiveData<Boolean> = _loginStatus

    init {
        viewModelScope.launch {
            try {
                val res = homeRepository.getHome()
                when (res.code()) {
                    200 -> _homeForm.value = res.body()?.data!!
                    403 -> _loginStatus.value = false
                    else -> _loginStatus.value = false
                }
            } catch (e: Exception) {
                Log.d("HomeService", "Exception 에러${e.message}")
                _loginStatus.value = false
            } catch (e: IOException) {
                Log.d("HomeService", "IO 에러${e.message}")
                _loginStatus.value = false
            } catch (e: NullPointerException) {
                Log.d("HomeService", "NullPointer 에러${e.message}")
                _loginStatus.value = false
            }

        }
    }


}