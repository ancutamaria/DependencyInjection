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

    /*
    scoped to the activity
     */
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val stackoverflowApi get() = appCompositionRoot.stackoverflowApi
    private val fragmentManager get() = activity.supportFragmentManager

    /*
    not scoped to the activity - here because there is no state that needs to be shared
     */

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}