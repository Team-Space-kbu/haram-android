package com.space.shared

data class UiStatus<T>(
    val uiUiStatusType: UiStatusType,
    val data: T? = null
)

enum class UiStatusType {
    ERROR,          //에러가 발생했을때
    LOADING,        //로딩중일때
    SUCCESS,        //성공
    REJECT          //서버에서 거부를 했을경우
}