package com.jshme.covidwidget.domain.repository

import com.jshme.covidwidget.domain.entity.CovidCounterEntity
import com.jshme.covidwidget.domain.Result
import kotlinx.coroutines.flow.Flow

interface CovidRepository {
    suspend fun getCovidCounter(): Flow<CovidCounterEntity>
}
