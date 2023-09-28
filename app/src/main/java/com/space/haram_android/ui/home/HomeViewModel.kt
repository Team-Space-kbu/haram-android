package com.space.haram_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.haram_android.util.ViewType
import com.space.data.res.home.HomeRes
import com.space.domain.usecase.home.HomeRepository
import com.space.haram_android.base.ViewTypeListener
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainImmediateDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainImmediateDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _homeFormState = MutableLiveData<HomeFormState>()
    val homeFormState: LiveData<HomeFormState> = _homeFormState

    private val _homeData = MutableLiveData<HomeRes>()
    val homeData: LiveData<HomeRes> = _homeData

    private val _eventViewType = MutableLiveData<ViewType>()
    val eventViewType: LiveData<ViewType> = _eventViewType

    private var offsetPx: Int? = null

    init {
        viewModelScope.launch {
            homeRepository.getHome().let { result ->
                when (result) {
                    is ResultData.Success<ResponseBody<HomeRes>> ->
                        result.body.data.let {
                            _homeData.value = it
                            _homeFormState.value = HomeFormState(homeStatus = true)
                        }

                    is ResultData.Error ->
                        _homeFormState.value = HomeFormState(loginStatus = false)

                    is ResultData.Unauthorized ->
                        _homeFormState.value = HomeFormState(loginStatus = false)

                }
            }

        }
    }

    val bindingListener = object : ViewTypeListener<ViewType> {
        override fun setViewType(t: ViewType) {
            Timber.d("set viewType, $t")
            _eventViewType.value = t
        }

        override fun clearViewType() {
            _eventViewType.value = ViewType.NOT_THING
        }
    }


    fun viewTypeVerify(): Boolean =
        ViewType.NOT_THING != eventViewType.value


    fun getOffsetPx() = offsetPx

    fun setOffsetPx(offset: Int) {
        offsetPx = offset
    }

}