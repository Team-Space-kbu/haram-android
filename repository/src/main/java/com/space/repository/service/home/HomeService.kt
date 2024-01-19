package com.space.repository.service.home

import com.space.shared.data.home.HomeInfo
import com.space.shared.data.home.Shortcut

interface HomeService {
    suspend fun getHome(): HomeInfo

    suspend fun getShortcut(): List<Shortcut>
}