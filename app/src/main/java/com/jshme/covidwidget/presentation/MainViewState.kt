package com.jshme.covidwidget.presentation

import com.jshme.covidwidget.domain.entity.CovidCounterEntity

sealed class MainViewState {
    object UnInitialized: MainViewState()
    object Loading: MainViewState()
    data class Success(val entity: CovidCounterEntity): MainViewState()
    data class Error(val msg: String): MainViewState()
}
