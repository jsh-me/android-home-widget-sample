package com.jshme.covidwidget.domain.entity

data class CountryNameEntity(
    val countryName: String,
    val newCase: String,
    val totalCase: String,
    val recovered: String,
    val death: String,
    val percentage: String,
    val newCcase: String,
    val newFcase: String,
)
