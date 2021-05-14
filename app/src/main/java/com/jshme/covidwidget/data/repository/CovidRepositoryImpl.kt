package com.jshme.covidwidget.data.repository

import com.jshme.covidwidget.data.ModelMapper
import com.jshme.covidwidget.data.api.CovidService
import com.jshme.covidwidget.domain.entity.CovidCounterEntity
import com.jshme.covidwidget.domain.repository.CovidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import com.jshme.covidwidget.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CovidRepositoryImpl @Inject constructor(
    private val service: CovidService
) : CovidRepository {

    override suspend fun getCovidCounter(): Flow<CovidCounterEntity> = flow {
         service.getCovidCounter().body()?.let { response ->
             val entity = ModelMapper.toEntity(response)
             emit(entity)
         }
    }.flowOn(Dispatchers.IO)
}
