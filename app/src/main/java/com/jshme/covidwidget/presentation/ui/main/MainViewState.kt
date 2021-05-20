package com.jshme.covidwidget.presentation.ui.main

import com.jshme.covidwidget.domain.entity.CountryCovidCounterEntity
import com.jshme.covidwidget.domain.entity.DomesticCovidCounterEntity

sealed class MainViewState {
    object UnInitialized: MainViewState()
    object Loading: MainViewState()
    data class Success(
        val domestic: DomesticCovidCounterEntity,
        val country: CountryCovidCounterEntity
    ): MainViewState()
    data class Error(val msg: String): MainViewState()
}
