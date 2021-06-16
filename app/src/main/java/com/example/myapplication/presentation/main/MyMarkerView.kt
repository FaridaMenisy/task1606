package com.example.myapplication.presentation.main

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.util.dateFormatter
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import kotlinx.android.synthetic.main.my_marker.view.*


class MyMarkerView(context: Context) : MarkerView(context, R.layout.my_marker) {

    override fun refreshContent(entry: Entry, highlight: Highlight) {
        super.refreshContent(entry, highlight)
        valueView.text   =  context.getString(R.string.value, entry.y.toString() )
        dateView.text = entry.x.dateFormatter()
    }
}