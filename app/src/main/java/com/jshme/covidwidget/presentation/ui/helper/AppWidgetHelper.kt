package com.jshme.covidwidget.presentation.ui.helper

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.jshme.covidwidget.R

class AppWidgetHelper(
    private val context: Context
) {
    private var title: String? = null
    private var description: String? = null
    private var pendingIntent: (() -> PendingIntent)? = null

    @DrawableRes
    private var iconRes: Int? = null
    private var widgetId: Int = 0

    private lateinit var appWidgetManager: AppWidgetManager

    fun onInitialize(title: String? = null, description: String? = null): AppWidgetHelper = apply {
        appWidgetManager = AppWidgetManager.getInstance(context)
        this.title = title
        this.description = description
    }

    fun setWidgetId(id: Int?): AppWidgetHelper = apply {
        this.widgetId = id ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }

    fun setTitleText(title: String?): AppWidgetHelper = apply {
        this.title = title
    }

    fun setIcon(@DrawableRes iconRes: Int?): AppWidgetHelper = apply {
        this.iconRes = iconRes
    }

    fun setDescriptionText(description: String?): AppWidgetHelper = apply {
        this.description = description
    }

    fun setOnClickListener(pendingIntent: () -> PendingIntent): AppWidgetHelper = apply {
        this.pendingIntent = pendingIntent
    }

    fun build() {
        RemoteViews(context.packageName, R.layout.layout_covid_appwidget).apply {
            setTextViewText(R.id.title, title ?: "")
            setTextViewText(R.id.description, description ?: "")
            setOnClickPendingIntent(R.id.root, pendingIntent?.invoke())
            iconRes?.let { icon -> setImageViewResource(R.id.icon, icon) }
        }.also { views ->
            appWidgetManager.updateAppWidget(widgetId, views)
        }

        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
        }

        (context as AppCompatActivity).apply {
            setResult(Activity.RESULT_OK, resultValue)
            finish()
        }
    }
}
