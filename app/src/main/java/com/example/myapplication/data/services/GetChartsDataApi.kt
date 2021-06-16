package com.example.myapplication.data.services

import com.example.myapplication.domain.model.ChartResponseRaw
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetChartsDataApi {
    //add base url
    @GET("charts/transactions-per-second")
    suspend fun getChartsData(
        @Query("timespan") timeSpan: String, @Query("rollingAverage") rollingAverage: String,
        @Query("format") format: String
    ): Response<ChartResponseRaw>
}