package com.am.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.am.dagger2course.MyApplication
import com.am.dagger2course.common.composition.ActivityCompositionRoot
import com.am.dagger2course.common.composition.PresentationCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    protected val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

}