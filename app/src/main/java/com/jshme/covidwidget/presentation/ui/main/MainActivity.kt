package com.jshme.covidwidget.presentation.ui.main

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.ActivityMainBinding
import com.jshme.covidwidget.presentation.customview.TrendType
import com.jshme.covidwidget.presentation.ui.add.AddWidgetActivity
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
        initViews()
        observeState()
    }

    private fun initViews() {
        dataBinding.addWidgetButton.setOnClickListener {
            startActivity(Intent(this, AddWidgetActivity::class.java))
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
                    setupFirstCardView(state.domestic.totalCaseBefore)
                    setupSecondCardView(state.country.korea.newCase)
                }
            }
        }
    }

    private fun setupFirstCardView(totalCaseBefore: Long) {
        when {
            totalCaseBefore > 0 -> {
                dataBinding.firstCardView.trendType = TrendType.UP
                dataBinding.firstCardView.description =
                    getString(R.string.confirmed_cardview_increase_person_description, abs(totalCaseBefore))
            }
            totalCaseBefore < 0 -> {
                dataBinding.firstCardView.trendType = TrendType.DOWN
                dataBinding.firstCardView.description =
                    getString(R.string.confirmed_cardview_decrease_person_description, abs(totalCaseBefore))
            }
            else -> {
                dataBinding.firstCardView.trendType = TrendType.MIDDLE
                dataBinding.firstCardView.description = getString(R.string.confirmed_cardview_no_change_person_description)
            }
        }
    }

    private fun setupSecondCardView(newCase: String) {
        dataBinding.secondCardView.icon = R.drawable.ic_happy
        dataBinding.secondCardView.descriptionVisibility = View.GONE
        dataBinding.secondCardView.title = getString(R.string.total_confirmed_parson_title, newCase)
    }
}
