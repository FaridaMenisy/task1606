package com.example.myapplication.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.item_card.view.*

class ChartsSummaryAdapter(private var chartSummaryData: MutableList<Pair<Float, String>>) :
    RecyclerView.Adapter<ChartsSummaryAdapter.ChartSummaryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChartsSummaryAdapter.ChartSummaryViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflatedView: View = inflater.inflate(R.layout.item_card, parent, false)
        return ChartSummaryViewHolder(inflatedView)


    }

    override fun getItemCount(): Int =
        chartSummaryData.size


    override fun onBindViewHolder(
        holder: ChartsSummaryAdapter.ChartSummaryViewHolder,
        position: Int
    ) {
        holder.bind(chartSummaryData[position])
    }

    inner class ChartSummaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chartSummaryData: Pair<Float, String>) {
            with(itemView) {
                chartSummaryData.apply {
                    tvUnit.text = context.getString(R.string.value, first.toString())
                    tvHightestRate.text = second
                }
            }

        }
    }
}