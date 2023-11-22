package com.space.domain.base

import com.space.data.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class NonParamUseCase<R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): ResultData<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute().let {
                    ResultData.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.d(e)
            ResultData.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}