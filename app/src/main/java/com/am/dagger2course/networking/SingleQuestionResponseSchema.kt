package com.am.dagger2course.networking

import com.am.dagger2course.questions.QuestionWithBody
import com.google.gson.annotations.SerializedName

data class SingleQuestionResponseSchema(@SerializedName("items") val questions: List<QuestionWithBody>) {
    val question: QuestionWithBody get() = questions[0]
}