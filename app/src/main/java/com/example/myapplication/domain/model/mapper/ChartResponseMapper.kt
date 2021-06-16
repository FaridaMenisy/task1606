package com.example.myapplication.domain.model.mapper

import com.example.myapplication.domain.model.ChartResponseRaw
import com.example.myapplication.domain.model.ChartValues
import com.example.myapplication.domain.model.ChartsDataModel
import com.github.mikephil.charting.data.Entry

class ChartResponseMapper {

    fun chartRawResponseToDomainResponse(chartResponseRaw: ChartResponseRaw) = ChartsDataModel(
        name = chartResponseRaw.name,
        description = chartResponseRaw.description,
        period = chartResponseRaw.period,
        values = listToEntry(chartResponseRaw.values),
        unit = chartResponseRaw.unit
    )

    private fun listToEntry(values: MutableList<ChartValues>): MutableList<Entry> {
        val list = mutableListOf<Entry>()
        values.forEachIndexed { index, chartValues ->
            list.add(index, Entry(chartValues.x, chartValues.y))
        }
        return list
    }

}