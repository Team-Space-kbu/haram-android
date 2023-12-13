package com.space.repository.service.impl

import com.space.shared.data.home.HomeInfo
import com.space.shared.data.home.Shortcut
import com.space.repository.service.inf.HomeService
import com.space.repository.api.HomeApi
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class HomeServiceImpl @Inject constructor(
    private val spaceHomeApi: HomeApi,
) : HomeService {
    override suspend fun getHome(): HomeInfo {
        return runBlocking {
            spaceHomeApi.getHome().data
        }
    }

    override suspend fun getShortcut(): List<Shortcut> {
        return runBlocking {
            spaceHomeApi.getShortcut()
        }
    }

}