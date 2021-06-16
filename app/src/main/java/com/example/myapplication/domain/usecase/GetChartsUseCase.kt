package com.example.myapplication.domain.usecase


import com.example.myapplication.data.GetChartsRepoImpl



class GetChartsUseCase(private  var getChartsRepo: GetChartsRepoImpl) {

    val chartsLiveData by lazy { getChartsRepo.chartsLiveData}
    suspend fun getChartsData(timeStamp: String, format :String, rollingAverage:String) {
         getChartsRepo.getChartsRepo(format =format, timeStamp = timeStamp, rollingAverage = rollingAverage)
    }
}