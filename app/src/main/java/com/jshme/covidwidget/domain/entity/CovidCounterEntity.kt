package com.jshme.covidwidget.domain.entity

data class CovidCounterEntity(
    val resultCode: String,
    val totalCase: String,
    val totalRecovered: String,
    val totalDeath: String,
    val nowCase: String,
    val city1n: String,
    val city2n: String,
    val city3n: String,
    val city4n: String,
    val city5n: String,
    val city1p: String,
    val city2p: String,
    val city3p: String,
    val city4p: String,
    val city5p: String
)
