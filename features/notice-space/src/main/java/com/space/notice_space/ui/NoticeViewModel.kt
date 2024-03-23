package com.space.notice_space.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.ntocie_space.NoticeBannerUseCase
import com.space.domain.usecase.ntocie_space.NoticeBiblesUseCase
import com.space.domain.usecase.ntocie_space.NoticeRothemUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.data.notice_space.BannerNotice
import com.space.shared.data.notice_space.BibleNotice
import com.space.shared.data.notice_space.NoticeSpace
import com.space.shared.data.rothem.RothemNotice
import com.space.shared.mapCatching
import com.space.shared.type.NoticeSpaceType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val noticeBiblesUseCase: NoticeBiblesUseCase,
    private val noticeBannerUseCase: NoticeBannerUseCase,
    private val noticeRothemUseCase: NoticeRothemUseCase
) : ViewModel() {


    private val _notice = MutableLiveData<NoticeSpace>()
    val ntoice: LiveData<NoticeSpace> = _notice

    @Inject
    lateinit var navigatorImage: NavigatorImage

    fun getBible(seq: String, type: NoticeSpaceType) {
        viewModelScope.launch {
            val result = async {
                when (type) {
                    NoticeSpaceType.BANNER -> noticeBannerUseCase(seq)
                    NoticeSpaceType.BIBLE -> noticeBiblesUseCase(seq)
                    NoticeSpaceType.ROTHEM -> noticeRothemUseCase(seq)
                }
            }.await()
            result.mapCatching(
                onSuccess = { data ->
                    when (data) {
                        is BibleNotice -> _notice.value = data.toNoticeSpace()
                        is BannerNotice -> _notice.value = data.toNoticeSpace()
                        is RothemNotice -> _notice.value = data.toSpaceNotice()
                    }
                },
                onError = { throwable ->
                    Timber.i(throwable.message)
                }
            )
        }
    }
}

