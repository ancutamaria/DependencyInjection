package com.am.dagger2course.questions

import com.am.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/* MVC - Model */
class FetchQuestionDetailsUseCase(
        private val stackoverflowApi: StackoverflowApi
) {

    suspend fun fetchQuestionDetails(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body().toString())
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}