package com.jshme.covidwidget.presentation.ui.add

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.ActivityAddWidgetBinding
import com.jshme.covidwidget.presentation.ui.add.SelectIconDialog.OnSelectIconDismissListener
import com.jshme.covidwidget.presentation.ui.helper.AppWidgetHelper
import com.jshme.covidwidget.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWidgetActivity : AppCompatActivity(), OnSelectIconDismissListener {

    private lateinit var dataBinding: ActivityAddWidgetBinding
    private val viewModel by viewModels<AddWidgetViewModel>()
    private var appWidgetId: Int? = null
    @DrawableRes private var iconResId: Int? = null

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

        dataBinding.saveButton.setOnClickListener {
            createAppWidget()
        }

        dataBinding.selectIcon.setOnClickListener {
            SelectIconDialog().show(supportFragmentManager, SelectIconDialog::class.java.simpleName)
        }
    }

    private fun createAppWidget() {
        appWidgetHelper.onInitialize()
            .setWidgetId(appWidgetId)
            .setTitleText(viewModel.titleText.value)
            .setDescriptionText(viewModel.descriptionText.value)
            .setIcon(iconResId)
            .setOnClickListener {
                Intent(this, MainActivity::class.java)
                    .let { intent ->
                        PendingIntent.getActivity(this, 0, intent, 0)
                    }
            }
            .build()
    }

    override fun onClickIcon(drawableRes: Int?) {
        iconResId = drawableRes
        dataBinding.rectangleCardView.icon = iconResId
    }
}
