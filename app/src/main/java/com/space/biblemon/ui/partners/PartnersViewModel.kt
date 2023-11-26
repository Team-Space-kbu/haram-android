package com.space.biblemon.ui.partners

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.biblemon.base.view.BaseViewModel
import data.partner.Partner
import com.space.domain.usecase.PartnersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersViewModel @Inject constructor(
    private val partnersUseCase: PartnersUseCase
) : BaseViewModel() {
    private val _partnersData = MutableLiveData<List<Partner>>()
    val partnersData: LiveData<List<Partner>> = _partnersData

    init {
        viewModelScope.launch {
            _partnersData.value = partnersUseCase.getPartners()
        }
    }
}