package com.jshme.covidwidget.domain.entity

data class DomesticCovidCounterEntity(
    val resultCode: String,
    val totalCase: Long,
    val totalRecovered: Long,
    val totalDeath: Long,
    val nowCase: Long,
    val city1n: String,
    val city2n: String,
    val city3n: String,
    val city4n: String,
    val city5n: String,
    val city1p: Double,
    val city2p: Double,
    val city3p: Double,
    val city4p: Double,
    val city5p: Double,
    val totalCaseBefore: Long
)
