package com.am.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.am.dagger2course.MyApplication

open class BaseActivity: AppCompatActivity() {

    val compositionRoot get() = (application as MyApplication).appCompositionRoot
}