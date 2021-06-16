package com.example.myapplication.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetChartsUseCase
import kotlinx.coroutines.launch

class ChartsViewModel(private var getChartsUseCase: GetChartsUseCase) : ViewModel() {


    val chartsLiveData by lazy { getChartsUseCase.chartsLiveData }
    fun getChartsData(timeSpan: String, format: String, rollingAverage: String) {
        viewModelScope.launch {
            getChartsUseCase.getChartsData(
                timeStamp = timeSpan,
                rollingAverage = rollingAverage,
                format = format
            )
        }
    }


}