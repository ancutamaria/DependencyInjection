package com.am.dagger2course.networking

import com.am.dagger2course.questions.Question
import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)