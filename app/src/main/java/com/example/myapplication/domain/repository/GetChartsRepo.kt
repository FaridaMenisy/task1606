package com.example.myapplication.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.nw.Result
import com.example.myapplication.domain.model.ChartsDataModel


interface GetChartsRepo {
    suspend fun getChartsRepo(format :String, timeStamp :String, rollingAverage :String)
}