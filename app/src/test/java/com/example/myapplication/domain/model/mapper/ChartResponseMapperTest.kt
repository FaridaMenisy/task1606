package com.example.myapplication.domain.model.mapper

import com.example.myapplication.test_utils.ChartsTestUtil
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test


internal class ChartResponseMapperTest {

    private lateinit var chartResponseMapper: ChartResponseMapper

    @Before
    fun setup() {
        chartResponseMapper = spy(ChartResponseMapper())
    }

    @Test
    fun chartRawResponseToDomainResponse() {
        whenever(ChartsTestUtil.getChartsSuccessRawResponse().body()?.let {
            chartResponseMapper.chartRawResponseToDomainResponse(
                it
            )
        }).thenReturn(ChartsTestUtil.getChartsDomainResponse())
    }

}