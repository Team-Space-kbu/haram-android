package com.space.partners.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.partners.PartnersUseCase
import com.space.shared.data.partner.Partner
import com.space.shared.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersViewModel @Inject constructor(
    private val partnersUseCase: PartnersUseCase
): ViewModel() {

    private val _partnersInfo: MutableLiveData<List<Partner>> = MutableLiveData<List<Partner>>()
    val partnersInfo: LiveData<List<Partner>> = _partnersInfo

    init {
        viewModelScope.launch {
            val info = async { partnersUseCase() }
            _partnersInfo.value = info.await().successOr(emptyList())
        }
    }

}