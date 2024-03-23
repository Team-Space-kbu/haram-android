package com.space.shared

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable: NetworkStatus()
}