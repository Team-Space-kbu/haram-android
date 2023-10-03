package com.space.biblemon.ui.partners

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.biblemon.base.view.BaseViewModel
import com.space.data.res.partners.PartnersReq
import com.space.domain.usecase.PartnersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersViewModel @Inject constructor(
    private val partnersUseCase: PartnersUseCase
) : BaseViewModel() {
    private val _partnersData = MutableLiveData<List<PartnersReq>>()
    val partnersData: LiveData<List<PartnersReq>> = _partnersData

    init {
        viewModelScope.launch {
            _partnersData.value = partnersUseCase.getPartners()
        }
    }
}