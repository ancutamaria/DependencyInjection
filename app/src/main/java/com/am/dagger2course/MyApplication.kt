package com.am.dagger2course

import android.app.Application
import com.am.dagger2course.common.composition.AppCompositionRoot
import com.am.dagger2course.networking.StackoverflowApi
import com.am.dagger2course.questions.FetchQuestionDetailsUseCase
import com.am.dagger2course.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    public lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }

}