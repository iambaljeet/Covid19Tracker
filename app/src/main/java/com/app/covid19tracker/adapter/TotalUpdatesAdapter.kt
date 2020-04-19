package com.app.covid19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.covid19tracker.R
import com.app.covid19tracker.model.GlobalDataModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.list_item_global_data.view.*

class TotalUpdatesAdapter :
    ListAdapter<GlobalDataModel, TotalUpdatesViewHolder>(DiffUtilsAllDataModel()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalUpdatesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_global_data, parent, false)
        return TotalUpdatesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TotalUpdatesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TotalUpdatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(globalDataModel: GlobalDataModel?) {
        itemView.text_view_confirmed_cases?.text = globalDataModel?.confirmed?.value?.toString()
        itemView.text_view_recovered_cases?.text = globalDataModel?.recovered?.value?.toString()
        itemView.text_view_deceased_cases?.text = globalDataModel?.deaths?.value?.toString()

        setCharts(itemView, globalDataModel)
    }
}

private fun setCharts(itemView: View, globalDataModel: GlobalDataModel?) {
    val PIE_ANIMATION_DURATION = 1500
    val PIE_RADIUS = 75f

    val confirmedCases = globalDataModel?.confirmed?.value?.toFloat() ?: 0f
    val recoveredCases = globalDataModel?.recovered?.value?.toFloat() ?: 0f
    val deceasedCases = globalDataModel?.deaths?.value?.toFloat() ?: 0f

    val context = itemView.context
    val pieDataSet = PieDataSet(
        listOf(
            PieEntry(confirmedCases, context.getString(R.string.confirmed)),
            PieEntry(recoveredCases, context.getString(R.string.recovered)),
            PieEntry(deceasedCases, context.getString(R.string.deceased))
        ), context.getString(R.string.covid_19_data)
    )

    val colors = arrayListOf(
        ContextCompat.getColor(itemView.context, R.color.colorConfirmed),
        ContextCompat.getColor(itemView.context, R.color.colorRecovered),
        ContextCompat.getColor(itemView.context, R.color.colorDeaths)
    )
    pieDataSet.colors = colors

    val pieData = PieData(pieDataSet)
    pieData.setDrawValues(false)
    with(itemView.chart_total_cases) {
        if (data == pieData) return
        data = pieData
        legend.isEnabled = false
        description = null
        holeRadius = PIE_RADIUS
        setHoleColor(ContextCompat.getColor(context, R.color.colorTransparent))
        setDrawEntryLabels(false)
        animateY(PIE_ANIMATION_DURATION, Easing.EaseInOutQuart)
        invalidate()
    }
}

class DiffUtilsAllDataModel : DiffUtil.ItemCallback<GlobalDataModel>() {
    override fun areItemsTheSame(oldItem: GlobalDataModel, newItem: GlobalDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GlobalDataModel, newItem: GlobalDataModel): Boolean {
        return oldItem == newItem
    }
}