package com.example.myapplication.data.source.remote

import com.example.myapplication.domain.model.ChartResponseRaw
import retrofit2.Response

interface GetChartsRemoteDataSource {
    suspend fun getChartsData(
        timeSpan: String,
        format: String,
        rollingAverage: String
    ): Response<ChartResponseRaw>
}