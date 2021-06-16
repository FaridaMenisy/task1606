package com.example.myapplication.data.source.remote

import com.example.myapplication.data.services.GetChartsDataApi
import com.example.myapplication.domain.model.ChartResponseRaw
import retrofit2.Response

class GetChartsRemoteDataSourceImpl(private var getChartsDataApi: GetChartsDataApi) :
    GetChartsRemoteDataSource {
    override suspend fun getChartsData(
        timeSpan: String,
        format: String,
        rollingAverage: String
    ): Response<ChartResponseRaw> {
       return  getChartsDataApi.getChartsData(
            timeSpan = timeSpan,
            format = format,
            rollingAverage = rollingAverage
        )
    }
}