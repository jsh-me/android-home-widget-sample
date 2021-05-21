package com.jshme.covidwidget.data.response
import com.google.gson.annotations.SerializedName
import com.jshme.covidwidget.domain.entity.DomesticCovidCounterEntity

data class DomesticCovidCounterResponse(
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
    fun toEntity(): DomesticCovidCounterEntity =
        DomesticCovidCounterEntity(
            resultCode = resultCode,
            totalCase = totalCase.toLongValue(),
            totalRecovered = totalRecovered.toLongValue(),
            totalDeath = totalDeath.toLongValue(),
            nowCase = nowCase.toLongValue(),
            city1n = city1n,
            city2n = city2n,
            city3n = city3n,
            city4n = city4n,
            city5n = city5n,
            city1p = city1p.toDouble(),
            city2p = city2p.toDouble(),
            city3p = city3p.toDouble(),
            city4p = city4p.toDouble(),
            city5p = city5p.toDouble(),
            totalCaseBefore = totalCaseBefore.toLongValue()
        )

    private fun String.toLongValue(): Long {
        return this.replace(("[^\\d.]").toRegex(), "").toLong()
    }

}
