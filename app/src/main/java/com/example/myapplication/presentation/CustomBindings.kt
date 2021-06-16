package com.example.myapplication.presentation

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import  com.example.myapplication.data.nw.Result

import com.example.myapplication.domain.model.ChartsDataModel
import com.example.myapplication.util.fillChartData
import com.example.myapplication.util.setupChartView
import com.example.myapplication.util.setupChartXAxis
import com.github.mikephil.charting.charts.LineChart

class CustomBindings {
    companion object {
        @BindingAdapter("loadingViewStatus")
        @JvmStatic
        fun loadingViewVisibility(
            viewGroup: ViewGroup,
            result: MutableLiveData<Result<ChartsDataModel>>
        ) {
            when (result.value) {
                is Result.Loading -> {
                    viewGroup.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    viewGroup.visibility = View.GONE
                }
                is Result.Error -> {
                    viewGroup.visibility = View.GONE
                }
                else ->
                    viewGroup.visibility = View.GONE
            }
        }


        @BindingAdapter("errorViewVisibility")
        @JvmStatic
        fun errorViewVisibility(
            viewGroup: ViewGroup,
            result: MutableLiveData<Result<ChartsDataModel>>
        ) {
            when (result.value) {
                is Result.Loading -> {
                    viewGroup.visibility = View.GONE
                }
                is Result.Success -> {
                    viewGroup.visibility = View.GONE
                }
                is Result.Error -> {
                    viewGroup.visibility = View.VISIBLE
                }
                else ->
                    viewGroup.visibility = View.GONE
            }
        }


        @BindingAdapter("fillLineChartData")
        @JvmStatic
        fun fillChartData(lineChart: LineChart, result: MutableLiveData<Result<ChartsDataModel>>) {
            when (result.value) {
                is Result.Success -> {
                    lineChart.apply {
                        setupChartView()
                        setupChartXAxis((result.value as Result.Success<ChartsDataModel>).data)
                        fillChartData(
                            (result.value as Result.Success<ChartsDataModel>).data,
                            lineChart.context
                        )
                    }


                }

            }
        }


        @BindingAdapter("cardViewVisibility")
        @JvmStatic
        fun cardViewVisibility(
            cardView: CardView,
            result: MutableLiveData<Result<ChartsDataModel>>
        ) {
            when (result.value) {
                is Result.Success -> {
                    cardView.visibility = View.VISIBLE
                }
                else -> cardView.visibility = View.GONE
            }
        }

        @BindingAdapter("textData")
        @JvmStatic
        fun cardViewVisibility(
            textView: TextView,
            result: MutableLiveData<Result<ChartsDataModel>>
        ) {
            when (result.value) {
                is Result.Success -> {
                    textView.text = (result.value as Result.Success<ChartsDataModel>).data.description
                }
            }
        }
    }
}