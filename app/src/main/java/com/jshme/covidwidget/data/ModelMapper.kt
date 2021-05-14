package com.jshme.covidwidget.data

import com.jshme.covidwidget.data.response.CovidCounterResponse
import com.jshme.covidwidget.domain.entity.CovidCounterEntity

object ModelMapper {
    fun fromEntity(entity: CovidCounterEntity): CovidCounterResponse = CovidCounterResponse(
        resultCode = entity.resultCode,
        totalCase = entity.totalCase,
        totalRecovered = entity.totalRecovered,
        totalDeath = entity.totalDeath,
        nowCase = entity.nowCase,
        city1n = entity.city1n,
        city2n = entity.city2n,
        city3n = entity.city3n,
        city4n = entity.city4n,
        city5n = entity.city5n,
        city1p = entity.city1p,
        city2p = entity.city2p,
        city3p = entity.city3p,
        city4p = entity.city4p,
        city5p = entity.city5p
    )

    fun toEntity(response: CovidCounterResponse): CovidCounterEntity =
        CovidCounterEntity(
            resultCode = response.resultCode,
            totalCase = response.totalCase,
            totalRecovered = response.totalRecovered,
            totalDeath = response.totalDeath,
            nowCase = response.nowCase,
            city1n = response.city1n,
            city2n = response.city2n,
            city3n = response.city3n,
            city4n = response.city4n,
            city5n = response.city5n,
            city1p = response.city1p,
            city2p = response.city2p,
            city3p = response.city3p,
            city4p = response.city4p,
            city5p = response.city5p
        )
}

