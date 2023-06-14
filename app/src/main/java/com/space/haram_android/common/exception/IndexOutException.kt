package com.space.haram_android.common.exception

class IndexOutException (
    override val message: String = "지정된 Index 범위를 벗어나거나, Indexr가 지정되지 않았습니다."
) : RuntimeException(message)