package com.example.myapplication.data

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.nw.Result
import com.example.myapplication.data.nw.SafeApiRequest
import com.example.myapplication.data.source.remote.GetChartsRemoteDataSource
import com.example.myapplication.domain.model.ChartsDataModel
import com.example.myapplication.domain.model.mapper.ChartResponseMapper
import com.example.myapplication.domain.repository.GetChartsRepo

class GetChartsRepoImpl(
    private var getChartsRemoteDataSource: GetChartsRemoteDataSource,
    private var chartsResponseMapper: ChartResponseMapper

) : GetChartsRepo,
    SafeApiRequest() {
     val chartsLiveData = MutableLiveData<Result<ChartsDataModel>>()

    override suspend fun getChartsRepo(
        format: String,
        timeStamp: String,
        rollingAverage: String
    ) {
        try {
            chartsLiveData.value = Result.Loading
            val response = apiRequest {
                getChartsRemoteDataSource.getChartsData(
                    timeSpan = timeStamp,
                    format = format,
                    rollingAverage = rollingAverage
                )
            }
            response?.let {
                chartsLiveData.value =
                    Result.Success(data =  chartsResponseMapper.chartRawResponseToDomainResponse(response))
            }

        } catch (e: Exception) {
            chartsLiveData.value = Result.Error(e)
        }


    }
}