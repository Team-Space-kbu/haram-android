package com.space.repository

import com.space.shared.data.home.HomeInfo
import com.space.shared.data.home.Shortcut

interface HomeService {
    suspend fun getHome(): HomeInfo

    suspend fun getShortcut(): List<Shortcut>

}