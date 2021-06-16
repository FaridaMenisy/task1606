package com.example.myapplication.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong


fun Float.dateFormatter() :String{
   return SimpleDateFormat("dd-MMM", Locale.ENGLISH).format(this.roundToLong())

}


