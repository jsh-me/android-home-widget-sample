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

    fun onInitialize(title: String? = null, description: String? = null): AppWidgetHelper {
        appWidgetManager = AppWidgetManager.getInstance(context)
        this.title = title
        this.description = description
        return this
    }

    fun setWidgetId(id: Int?): AppWidgetHelper {
        this.widgetId = id ?: AppWidgetManager.INVALID_APPWIDGET_ID
        return this
    }

    fun setTitleText(title: String?): AppWidgetHelper {
        this.title = title
        return this
    }

    fun setIcon(@DrawableRes iconRes: Int?): AppWidgetHelper {
        this.iconRes = iconRes
        return this
    }

    fun setDescriptionText(description: String?): AppWidgetHelper {
        this.description = description
        return this
    }

    fun setOnClickListener(pendingIntent: () -> PendingIntent): AppWidgetHelper {
        this.pendingIntent = pendingIntent
        return this
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
