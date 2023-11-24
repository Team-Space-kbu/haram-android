package com.space.biblemon.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.biblemon.util.ViewType
import com.space.data.response.home.HomeInfo
import com.space.biblemon.base.listener.ViewTypeListener
import com.space.biblemon.base.listener.onClickEventListener
import com.space.shared.annotation.IoDispatcher
import com.space.shared.annotation.MainImmediateDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainImmediateDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _homeFormState = MutableLiveData<HomeFormState>()
    val homeFormState: LiveData<HomeFormState> = _homeFormState

    private val _homeData = MutableLiveData<HomeInfo>()
    val homeData: LiveData<HomeInfo> = _homeData

    private val _eventViewType = MutableLiveData<ViewType>()
    val eventViewType: LiveData<ViewType> = _eventViewType

    private val _eventKokkoks = MutableLiveData<String>()
    val eventKokkoks: LiveData<String> = _eventKokkoks

    private var offsetPx: Int? = null

    val bindingListener = object : ViewTypeListener<ViewType> {

        override fun setViewType(t: ViewType) {
            Timber.d("set viewType, $t")
            _eventViewType.value = t
        }

        override fun clearViewType() {
            _eventViewType.value = ViewType.NOT_THING
        }
    }

    val onClickKokkoks = object : onClickEventListener<String> {
        override fun apply(t: String) {
            _eventKokkoks.value = t
        }

    }

    init {
        viewModelScope.launch {
            homeUseCase.getHome().let { result ->
                when (result) {
                    is ResultData.Success<ResponseBody<HomeInfo>> ->
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

    fun viewTypeVerify(): Boolean =
        ViewType.NOT_THING != eventViewType.value

    fun getOffsetPx() = offsetPx

    fun setOffsetPx(offset: Int) {
        offsetPx = offset
    }

}