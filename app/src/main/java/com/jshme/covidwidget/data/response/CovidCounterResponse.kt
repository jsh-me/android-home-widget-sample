package com.jshme.covidwidget.data.response

import com.google.gson.annotations.SerializedName
import com.jshme.covidwidget.domain.entity.CovidCounterEntity

data class CovidCounterResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("TotalCase")
    val totalCase: String,
    @SerializedName("TotalRecovered")
    val totalRecovered: String,
    @SerializedName("TotalDeath")
    val totalDeath: String,
    @SerializedName("NowCase")
    val nowCase: String,
    @SerializedName("city1n")
    val city1n: String,
    @SerializedName("city2n")
    val city2n: String,
    @SerializedName("city3n")
    val city3n: String,
    @SerializedName("city4n")
    val city4n: String,
    @SerializedName("city5n")
    val city5n: String,
    @SerializedName("city1p")
    val city1p: String,
    @SerializedName("city2p")
    val city2p: String,
    @SerializedName("city3p")
    val city3p: String,
    @SerializedName("city4p")
    val city4p: String,
    @SerializedName("city5p")
    val city5p: String,
    @SerializedName("TotalCaseBefore")
    val totalCaseBefore: String
) {
    fun toEntity(response: CovidCounterResponse): CovidCounterEntity =
        CovidCounterEntity(
            resultCode = response.resultCode,
            totalCase = response.totalCase.toLong(),
            totalRecovered = response.totalRecovered,
            totalDeath = response.totalDeath,
            nowCase = response.nowCase,
            city1n = response.city1n,
            city2n = response.city2n,
            city3n = response.city3n,
            city4n = response.city4n,
            city5n = response.city5n,
            city1p = response.city1p.toDouble(),
            city2p = response.city2p.toDouble(),
            city3p = response.city3p.toDouble(),
            city4p = response.city4p.toDouble(),
            city5p = response.city5p.toDouble(),
            totalCaseBefore = response.totalCaseBefore.toLong()
        )
}
