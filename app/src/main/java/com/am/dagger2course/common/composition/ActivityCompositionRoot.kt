package com.am.dagger2course.common.composition

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.am.dagger2course.questions.FetchQuestionDetailsUseCase
import com.am.dagger2course.questions.FetchQuestionsUseCase
import com.am.dagger2course.screens.common.ScreensNavigator
import com.am.dagger2course.screens.common.dialogs.DialogsNavigator

/*
Activity Scope -> activity lifecycle
 */
class ActivityCompositionRoot(
        private val activity: AppCompatActivity,
        private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val fragmentManager get() = activity.supportFragmentManager

}