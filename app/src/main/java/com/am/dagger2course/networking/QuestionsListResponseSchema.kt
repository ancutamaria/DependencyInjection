package com.am.dagger2course.networking

import com.google.gson.annotations.SerializedName
import com.am.dagger2course.questions.Question

class QuestionsListResponseSchema(@SerializedName("items") val questions: List<Question>)