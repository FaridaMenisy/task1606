package com.example.myapplication.domain.model

import com.github.mikephil.charting.data.Entry


data class ChartResponseRaw (var status :String, var name : String , var unit :String, var period : String, var description :String,
                          var values : MutableList<ChartValues>)

data class ChartValues(var x : Float, var y :Float)

data class ChartsDataModel (var name: String, var period: String, var description: String, var values: MutableList<Entry>,var unit :String)



