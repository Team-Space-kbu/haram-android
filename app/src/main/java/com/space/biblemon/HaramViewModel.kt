package com.space.biblemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.auth.AuthStateUseCase
import com.space.shared.result.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HaramViewModel @Inject constructor(
    private val authStateUseCase: AuthStateUseCase,
) : ViewModel() {
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    init {
        viewModelScope.launch {
            val state = authStateUseCase().successOr(false)
            if (state) {
                Timber.d("Access token valid!!")
                _loginState.value = true
            } else {
                Timber.d("Access not found!!")
                _loginState.value = false
            }
        }

    }
}