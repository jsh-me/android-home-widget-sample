package com.jshme.covidwidget.data.api

import com.jshme.covidwidget.PrivateConst
import com.jshme.covidwidget.data.response.CovidCounterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService {

    @GET("korea")
    suspend fun getCovidCounter(
        @Query("serviceKey") serviceKey: String = PrivateConst.SECRET_KEY
    ): Response<CovidCounterResponse>

}
