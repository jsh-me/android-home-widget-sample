package com.jshme.covidwidget.domain.repository

import com.jshme.covidwidget.domain.entity.CountryCovidCounterEntity
import com.jshme.covidwidget.domain.entity.DomesticCovidCounterEntity
import kotlinx.coroutines.flow.Flow

interface CovidRepository {
    suspend fun getDomesticCovidCounter(): Flow<DomesticCovidCounterEntity>

    suspend fun getCountryCovidCounter(): Flow<CountryCovidCounterEntity>
}
