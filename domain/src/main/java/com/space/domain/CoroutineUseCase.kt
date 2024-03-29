package com.space.domain

import com.space.shared.ResultData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

///////////////////////////////////////////////////////////////////////////
// Origin Source : https://github.com/google/iosched/blob/main/shared/src/main/java/com/google/samples/apps/iosched/shared/domain/CoroutineUseCase.kt
///////////////////////////////////////////////////////////////////////////

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class UseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): ResultData<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                execute(parameters).let {
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
    protected abstract suspend fun execute(param: P): R
}
