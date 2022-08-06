package com.am.dagger2course.common.composition

import com.am.dagger2course.questions.FetchQuestionDetailsUseCase
import com.am.dagger2course.questions.FetchQuestionsUseCase
import com.am.dagger2course.screens.common.dialogs.DialogsNavigator

/*
Presentation/Controller Scope - glorified factory
 */
class PresentationCompositionRoot(
    private val activityCompositionRoot: ActivityCompositionRoot
) {

    private val fragmentManager = activityCompositionRoot.fragmentManager
    private val stackoverflowApi = activityCompositionRoot.stackoverflowApi

    val screensNavigator get() = activityCompositionRoot.screensNavigator

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackoverflowApi)
    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

}