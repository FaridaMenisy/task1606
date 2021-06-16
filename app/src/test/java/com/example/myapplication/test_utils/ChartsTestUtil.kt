package com.example.myapplication.test_utils

import com.example.myapplication.domain.model.ChartResponseRaw
import com.example.myapplication.domain.model.ChartsDataModel

import retrofit2.Response

class ChartsTestUtil {
    companion object{
    fun getChartsSuccessRawResponse(): Response<ChartResponseRaw> = Response.success(  ChartResponseRaw(name = "", unit = "", status = "", values = mutableListOf(), period = "",description = ""))

        fun getChartsDomainResponse() = ChartsDataModel(name = "", description = "",period = "",values = mutableListOf(),unit = "")


}


}