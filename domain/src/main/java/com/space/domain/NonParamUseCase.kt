package com.space.domain

import com.google.gson.Gson
import com.space.shared.ResultData
import com.space.shared.exception.user.LogoutProcessed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class NonParamUseCase<R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    private val gson = Gson()


    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     */
    suspend operator fun invoke(): ResultData<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute().let {
                    ResultData.Success(it)
                }
            }
        } catch (e: Throwable) {
            Timber.d(e)
            when (e.cause) {
                is com.space.shared.exception.user.LogoutProcessed -> ResultData.Error(
                    com.space.shared.exception.user.LogoutProcessed(
                        e.message!!
                    )
                )
                else -> ResultData.Error(e)
            }
        }
    }


    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}