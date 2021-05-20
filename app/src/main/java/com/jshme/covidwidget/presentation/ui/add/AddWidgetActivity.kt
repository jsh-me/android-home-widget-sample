package com.jshme.covidwidget.presentation.ui.add

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.ActivityAddWidgetBinding
import com.jshme.covidwidget.databinding.ActivityMainBinding
import com.jshme.covidwidget.presentation.ui.main.MainActivity
import com.jshme.covidwidget.presentation.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddWidgetActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityAddWidgetBinding
    private val viewModel by viewModels<AddWidgetViewModel>()
    private var appWidgetId: Int? = null

    private val appWidgetHelper: AppWidgetHelper by lazy {
        AppWidgetHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_widget)
        dataBinding.vm = viewModel
        dataBinding.lifecycleOwner = this

        initData()

    }

    private fun initData() {
        appWidgetId = intent.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        dataBinding.saveText.setOnClickListener {
            createAppWidget()
        }
    }

    private fun createAppWidget() {
        appWidgetHelper.onInitialize()
            .setWidgetId(appWidgetId)
            .setTitleText(viewModel.titleText.value)
            .setDescriptionText(viewModel.descriptionText.value)
            .setOnClickListener {
                Intent(this, MainActivity::class.java)
                    .let { intent ->
                        PendingIntent.getActivity(this, 0, intent, 0)
                    }
            }
            .build()
    }
}
