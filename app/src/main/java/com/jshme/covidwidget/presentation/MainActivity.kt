package com.jshme.covidwidget.presentation

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Outline
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.ActivityMainBinding
import com.jshme.covidwidget.domain.entity.CovidCounterEntity
import com.jshme.covidwidget.presentation.customview.TrendType
import com.jshme.covidwidget.presentation.widget.AppWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        observeState()

        // 위젯 크기에 따라 델리게이트 패턴으로 나누어볼 것
        val appWidgetManager: AppWidgetManager? = getSystemService(AppWidgetManager::class.java)
        val myProvider = ComponentName(this, AppWidget::class.java)
        val successCallback: PendingIntent? = if (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                appWidgetManager!!.isRequestPinAppWidgetSupported
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        ) {
            Intent(this, MainActivity::class.java).let { intent ->
                PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        } else {
            null
        }


        dataBinding.firstCardView.setOnClickListener {
            successCallback?.also { pendingIntent ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    appWidgetManager.requestPinAppWidget(myProvider, null, pendingIntent)
                }
            }
        }

    }

    private fun observeState() {
        viewModel.mainViewState.observe(this) { state ->
            when (state) {
                is MainViewState.Loading -> {
                    dataBinding.loadingContainer.visibility = View.VISIBLE
                }

                is MainViewState.Error -> {
                    dataBinding.loadingContainer.visibility = View.GONE
                }

                is MainViewState.Success -> {
                    dataBinding.loadingContainer.visibility = View.GONE
                    setupFirstCardView(state.entity.totalCaseBefore)
                    setupSecondCardView(state.entity.totalCase)
                }
            }
        }
    }

    private fun setupFirstCardView(totalCaseBefore: Long) {
        when {
            totalCaseBefore > 0 -> {
                dataBinding.firstCardView.trendType = TrendType.UP
                dataBinding.firstCardView.description = getString(R.string.confirmed_cardview_increase_person_description, abs(totalCaseBefore))
            }
            totalCaseBefore < 0 -> {
                dataBinding.firstCardView.trendType = TrendType.DOWN
                dataBinding.firstCardView.description = getString(R.string.confirmed_cardview_decrease_person_description, abs(totalCaseBefore))
            }
            else -> {
                dataBinding.firstCardView.trendType = TrendType.MIDDLE
                dataBinding.firstCardView.description = getString(R.string.confirmed_cardview_no_change_person_description)
            }
        }
    }

    private fun setupSecondCardView(totalCase: Long) {
        when {
            totalCase > 0 -> {
                dataBinding.secondCardView.icon = R.drawable.ic_happy
            }
            totalCase < 0 -> {
                dataBinding.secondCardView.icon = R.drawable.ic_happy
            }
            else -> {
                dataBinding.secondCardView.icon = R.drawable.ic_happy
            }
        }
        dataBinding.secondCardView.descriptionVisibility = View.GONE
        dataBinding.secondCardView.title = getString(R.string.total_confirmed_parson_title, totalCase)
    }
}
