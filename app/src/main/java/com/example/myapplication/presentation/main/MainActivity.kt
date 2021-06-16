package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.data.nw.Result
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.ChartsDataModel
import com.example.myapplication.util.Constants
import com.example.myapplication.util.Constants.FORMAT
import com.example.myapplication.util.Constants.ROLLING_AVG
import com.example.myapplication.util.Constants.TIME_SPAN
import com.github.mikephil.charting.data.Entry
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    // Lazy Inject ViewModel
    private val chartsViewModel: ChartsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //handling data binding for the view
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = chartsViewModel
        binding.lifecycleOwner = this@MainActivity
        setupObservers()
    }

    private fun setupObservers() {

        val observer = Observer<Result<ChartsDataModel>> { response ->
            when (response) {
                is Result.Success -> {
                    val chartsDataList = mutableListOf<Pair<Float, String>>()
                    chartsDataList.add(0,getMax(response.data.values))
                    chartsDataList.add(1,getMin(response.data.values))
                    rvData.adapter =
                        ChartsSummaryAdapter(chartsDataList)
                }
            }

        }
        chartsViewModel.chartsLiveData.observe(this, observer)
        chartsViewModel.getChartsData(
            timeSpan = TIME_SPAN,
            rollingAverage = ROLLING_AVG,
            format = FORMAT
        )
    }

    private fun getMax(value: MutableList<Entry>): Pair<Float, String> {
        var max = Float.MIN_VALUE
        for (i in value) {
            max = max.coerceAtLeast(i.y)
        }
        return Pair(first = max, second = Constants.HIGHEST_RATE)
    }

    private fun getMin(value: MutableList<Entry>): Pair<Float, String> {
        var min = Float.MAX_VALUE
        for (i in value) {
            min = min.coerceAtMost(i.y)
        }
        return Pair(first = min, second = Constants.LOWEST_RATE)
    }

}


