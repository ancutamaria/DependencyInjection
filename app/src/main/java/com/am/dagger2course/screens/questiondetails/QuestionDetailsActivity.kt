package com.am.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.am.dagger2course.R
import com.am.dagger2course.questions.FetchQuestionDetailsUseCase
import com.am.dagger2course.screens.common.dialogs.DialogsNavigator
import com.am.dagger2course.screens.common.dialogs.ServerErrorDialogFragment
import com.am.dagger2course.screens.common.toolbar.MyToolbar
import kotlinx.coroutines.*

class QuestionDetailsActivity : AppCompatActivity(), QuestionDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var toolbar: MyToolbar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var txtQuestionBody: TextView

    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var dialogsNavigator: DialogsNavigator

    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMvc = QuestionDetailsViewMvc(LayoutInflater.from(this), null)
        fetchQuestionDetailsUseCase = FetchQuestionDetailsUseCase()
        dialogsNavigator = DialogsNavigator(supportFragmentManager)

        setContentView(viewMvc.rootView)

        txtQuestionBody = findViewById(R.id.txt_question_body)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener { onBackPressed() }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionDetailsUseCase.fetchQuestionDetails(questionId)) {
                    is com.am.dagger2course.questions.Result.Success -> {
                        viewMvc.bindQuestionWithBody(result.payload as String)
                    }
                    is com.am.dagger2course.questions.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onBackClicked() {
        onBackPressed()
    }
}