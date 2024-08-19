package com.space.data.service.home

import com.space.shared.data.home.HomeInfo

interface HomeService {
    suspend fun getHome(): HomeInfo
}