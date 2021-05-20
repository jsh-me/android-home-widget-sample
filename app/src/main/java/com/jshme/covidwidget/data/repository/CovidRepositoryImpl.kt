package com.jshme.covidwidget.data.repository

import com.jshme.covidwidget.data.api.CovidService
import com.jshme.covidwidget.domain.entity.CountryCovidCounterEntity
import com.jshme.covidwidget.domain.entity.DomesticCovidCounterEntity
import com.jshme.covidwidget.domain.repository.CovidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CovidRepositoryImpl @Inject constructor(
    private val service: CovidService
) : CovidRepository {

    override suspend fun getDomesticCovidCounter(): Flow<DomesticCovidCounterEntity> = flow {
         service.getDomesticCovidCounter().body()?.let { response ->
             val entity = response.toEntity()
             emit(entity)
         }
    }.flowOn(Dispatchers.IO)

    override suspend fun getCountryCovidCounter(): Flow<CountryCovidCounterEntity> = flow {
        service.getCountryCovidCounter().body()?.let { response ->
            val entity = response.toEntity()
            emit(entity)
        }
    }.flowOn(Dispatchers.IO)
}
