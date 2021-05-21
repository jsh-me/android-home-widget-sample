package com.jshme.covidwidget.data.response

import com.google.gson.annotations.SerializedName
import com.jshme.covidwidget.domain.entity.CountryNameEntity

data class CountryNameResponse(
    @SerializedName("countryName")
    val countryName: String,
    @SerializedName("newCase")
    val newCase: String,
    @SerializedName("totalCase")
    val totalCase: String,
    @SerializedName("recovered")
    val recovered: String,
    @SerializedName("death")
    val death: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("newCcase")
    val newCcase: String,
    @SerializedName("newFcase")
    val newFcase: String,
){
    fun toEntity() = CountryNameEntity(
        countryName,
        newCase,
        totalCase,
        recovered,
        death,
        percentage,
        newCcase,
        newFcase
    )
}
