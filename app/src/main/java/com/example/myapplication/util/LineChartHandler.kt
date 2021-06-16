package com.example.myapplication.util

import android.content.Context
import android.graphics.Color
import com.example.myapplication.domain.model.ChartsDataModel
import com.example.myapplication.presentation.main.MyMarkerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter


fun LineChart.fillChartData(chartData: ChartsDataModel, context: Context) {

    description.text = chartData.unit


    val dataSet = LineDataSet(chartData.values, chartData.name)
    data = LineData(dataSet)
    marker = MyMarkerView(context)
    dataSet.apply {
        this.color = Color.BLACK
        valueTextColor = Color.GRAY
        highLightColor = Color.RED
        setDrawValues(false)
        lineWidth = 1.5f
        isHighlightEnabled = true
        setDrawHighlightIndicators(false)
    }


}

fun LineChart.setupChartXAxis(chartData: ChartsDataModel) {
    val xAxis: XAxis = getXAxis()
    xAxis.valueFormatter = object :
        ValueFormatter() {
        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            return value.toString()
        }

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return if (value.toInt() >= 0) value.dateFormatter() else {
                ("").toString()
            }
        }
    }
    xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
    xAxis.textSize = 8f
    xAxis.textColor = android.graphics.Color.RED
    xAxis.granularity = 1f


    xAxis.setDrawGridLines(false)
}

fun LineChart.setupChartView() {
    setTouchEnabled(true)
    isDragEnabled = true
    setScaleEnabled(true)
    setPinchZoom(true)
    axisRight.isEnabled = false
    xAxis.isEnabled = true
    description.isEnabled = true
}