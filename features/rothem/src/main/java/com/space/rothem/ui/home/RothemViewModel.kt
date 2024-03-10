package com.space.rothem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.rothem.RothemHome
import com.space.shared.data.rothem.Rothem
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RothemViewModel @Inject constructor(
    private val rothemHome: RothemHome
) : ViewModel() {

    private val _rothem: MutableLiveData<Rothem> = MutableLiveData<Rothem>()
    val rothem: LiveData<Rothem> = _rothem

    init {
        getRothemHome()
    }
    fun getRothemHome(){
        viewModelScope.launch {
            val result = async { rothemHome() }.await()
            result.mapCatching(
                onSuccess = { rothem ->
                    _rothem.value = rothem
                    Timber.d(rothem.toString())
                },
                onError = { error ->
                    Timber.d(error.message)
                }
            )

        }
    }
}