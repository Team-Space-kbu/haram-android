package com.space.repository

import data.home.HomeInfo
import data.home.Shortcut

interface HomeService {
    suspend fun getHome(): HomeInfo

    suspend fun getShortcut(): List<Shortcut>

}