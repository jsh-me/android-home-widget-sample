package com.jshme.covidwidget.domain.entity

data class CountryCovidCounterEntity(
    val resultCode: String,
    val resultMessage: String,
    val korea: CountryNameEntity,
    val seoul: CountryNameEntity,
    val busan: CountryNameEntity,
    val daegu: CountryNameEntity,
    val incheon: CountryNameEntity,
    val gwangju: CountryNameEntity,
    val daejeon: CountryNameEntity,
    val ulsan: CountryNameEntity,
    val sejong: CountryNameEntity,
    val gyeonggi: CountryNameEntity,
    val gangwon: CountryNameEntity,
    val chungbuk: CountryNameEntity,
    val chungnam: CountryNameEntity,
    val jeonbuk: CountryNameEntity,
    val jeonnam: CountryNameEntity,
    val gyeongbuk: CountryNameEntity,
    val gyeongnam: CountryNameEntity,
    val jeju: CountryNameEntity,
    val quarantine: CountryNameEntity
)
