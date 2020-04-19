package com.app.covid19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.covid19tracker.R
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.utility.getFormattedDate
import kotlinx.android.synthetic.main.list_item_daily_data.view.*

class DailyUpdatesAdapter :
    ListAdapter<DailyDataModel.DailyDataModelItem, DailyUpdatesViewHolder>(DiffUtilsDailyDataModelItem()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyUpdatesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_daily_data, parent, false)
        return DailyUpdatesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyUpdatesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DailyUpdatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(countryCaseModel: DailyDataModel.DailyDataModelItem?) {
        itemView.text_view_confirmed_cases?.text =
            countryCaseModel?.confirmed?.total?.toString()
        itemView.text_view_recovered_cases?.text =
            countryCaseModel?.recovered?.total?.toString()
        itemView.text_view_deceased_cases?.text = countryCaseModel?.deaths?.total?.toString()

        val lastUpdateTime = countryCaseModel?.reportDate?.let {
            getFormattedDate(
                it,
                "yyyy-MM-dd",
                "MMM d, yy"
            )
        }

        itemView.text_view_date.text = "Reported date: $lastUpdateTime"
    }
}

class DiffUtilsDailyDataModelItem : DiffUtil.ItemCallback<DailyDataModel.DailyDataModelItem>() {
    override fun areItemsTheSame(
        oldItem: DailyDataModel.DailyDataModelItem,
        newItem: DailyDataModel.DailyDataModelItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DailyDataModel.DailyDataModelItem,
        newItem: DailyDataModel.DailyDataModelItem
    ): Boolean {
        return oldItem == newItem
    }
}