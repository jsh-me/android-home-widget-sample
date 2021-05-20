package com.jshme.covidwidget.data.api

import com.jshme.covidwidget.PrivateConst
import com.jshme.covidwidget.data.response.CountryCovidCounterResponse
import com.jshme.covidwidget.data.response.DomesticCovidCounterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService {

    @GET("korea")
    suspend fun getDomesticCovidCounter(
        @Query("serviceKey") serviceKey: String = PrivateConst.SECRET_KEY
    ): Response<DomesticCovidCounterResponse>

    @GET("korea/country/new")
    suspend fun getCountryCovidCounter(
        @Query("serviceKey") serviceKey: String = PrivateConst.SECRET_KEY
    ): Response<CountryCovidCounterResponse>

}
