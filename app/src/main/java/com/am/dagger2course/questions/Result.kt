package com.am.dagger2course.questions

sealed class Result {
    class Success(val payload: Any) : Result()
    object Failure: Result()
}