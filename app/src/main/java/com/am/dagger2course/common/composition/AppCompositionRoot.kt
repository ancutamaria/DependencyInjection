package com.am.dagger2course.common.composition

import com.am.dagger2course.Constants
import com.am.dagger2course.networking.StackoverflowApi
import com.am.dagger2course.questions.FetchQuestionDetailsUseCase
import com.am.dagger2course.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

    public val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    public val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}