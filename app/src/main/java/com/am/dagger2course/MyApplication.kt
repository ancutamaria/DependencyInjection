package com.am.dagger2course

import android.app.Application
import com.am.dagger2course.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    public val stackoverflowApi: StackoverflowApi  = retrofit.create(StackoverflowApi::class.java)

    override fun onCreate() {
        super.onCreate()
    }

}