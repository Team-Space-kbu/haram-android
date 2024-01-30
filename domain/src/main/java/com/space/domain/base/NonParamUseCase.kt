package com.space.domain.base

import com.space.shared.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class NonParamUseCase<R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
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