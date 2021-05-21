package com.jshme.covidwidget.data.response

import com.google.gson.annotations.SerializedName
import com.jshme.covidwidget.domain.entity.CountryCovidCounterEntity

data class CountryCovidCounterResponse(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMessage")
    val resultMessage: String,
    @SerializedName("korea")
    val korea: CountryNameResponse,
    @SerializedName("seoul")
    val seoul: CountryNameResponse,
    @SerializedName("busan")
    val busan: CountryNameResponse,
    @SerializedName("daegu")
    val daegu: CountryNameResponse,
    @SerializedName("incheon")
    val incheon: CountryNameResponse,
    @SerializedName("gwangju")
    val gwangju: CountryNameResponse,
    @SerializedName("daejeon")
    val daejeon: CountryNameResponse,
    @SerializedName("ulsan")
    val ulsan: CountryNameResponse,
    @SerializedName("sejong")
    val sejong: CountryNameResponse,
    @SerializedName("gyeonggi")
    val gyeonggi: CountryNameResponse,
    @SerializedName("gangwon")
    val gangwon: CountryNameResponse,
    @SerializedName("chungbuk")
    val chungbuk: CountryNameResponse,
    @SerializedName("chungnam")
    val chungnam: CountryNameResponse,
    @SerializedName("jeonbuk")
    val jeonbuk: CountryNameResponse,
    @SerializedName("jeonnam")
    val jeonnam: CountryNameResponse,
    @SerializedName("gyeongbuk")
    val gyeongbuk: CountryNameResponse,
    @SerializedName("gyeongnam")
    val gyeongnam: CountryNameResponse,
    @SerializedName("jeju")
    val jeju: CountryNameResponse,
    @SerializedName("quarantine")
    val quarantine: CountryNameResponse
) {
    fun toEntity(): CountryCovidCounterEntity =
        CountryCovidCounterEntity(
            resultCode,
            resultMessage,
            korea.toEntity(),
            seoul.toEntity(),
            busan.toEntity(),
            daegu.toEntity(),
            incheon.toEntity(),
            gwangju.toEntity(),
            daejeon.toEntity(),
            ulsan.toEntity(),
            sejong.toEntity(),
            gyeonggi.toEntity(),
            gangwon.toEntity(),
            chungbuk.toEntity(),
            chungnam.toEntity(),
            jeonbuk.toEntity(),
            jeonnam.toEntity(),
            chungbuk.toEntity(),
            gyeongnam.toEntity(),
            jeju.toEntity(),
            quarantine.toEntity()
        )
}
