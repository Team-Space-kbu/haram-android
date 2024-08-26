package com.space.signup.ui.policy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.singup.PolicySignupUseCase
import com.space.shared.data.auth.UserPolicy
import com.space.shared.data.rothem.RothemPolicy
import com.space.shared.mapCatching
import com.space.shared.model.PolicyReqModel
import com.space.shared.model.UserTerms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PolicyViewModel @Inject constructor(
    private val policySignupUseCase: PolicySignupUseCase
) : ViewModel() {

    private val _policySingup = MutableLiveData<List<UserPolicy>>()
    val policySingup: LiveData<List<UserPolicy>> = _policySingup

    private val policyData: MutableMap<Int, Boolean> = mutableMapOf()
    var userPolicy: List<UserTerms> = emptyList()

    val toastMessage = MutableLiveData<String>()
    val uiStatus = MutableLiveData<Boolean>()


    init {
        viewModelScope.launch {
            val result = async { policySignupUseCase() }.await()
            result.mapCatching(
                onSuccess = {
                    _policySingup.value = it
                },
                onError = {
                    Timber.i(it.message)
                    toastMessage.value = "알 수 없는 오류가 발생했습니다."
                }
            )
        }
    }

    fun clearPolicy(){
        policyData.clear()
    }

    fun policy() {
        if (isPolicyChecked()) {
            userPolicy =
                policyData.map { UserTerms(it.key, if (it.value) "Y" else "N") }.toList()
            uiStatus.value = true
        } else {
            toastMessage.value = "약관을 모두 동의해주시기 바랍니다."
            uiStatus.value = false
        }
    }


    fun setPolicy(rothemPolicy: UserPolicy, isChecked: Boolean) {
        policyData[rothemPolicy.termsSeq] = isChecked
    }

    private fun isPolicyChecked(): Boolean {
        val policy = policySingup.value ?: return false
        return policy.all { response ->
            !response.isRequired || policyData[response.termsSeq] == true
        }
    }
}