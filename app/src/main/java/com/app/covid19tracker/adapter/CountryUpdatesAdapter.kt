package com.app.covid19tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.covid19tracker.R
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.utility.getFormattedDate
import kotlinx.android.synthetic.main.list_item_my_country_data.view.*
import java.util.*

class CountryUpdatesAdapter :
    ListAdapter<CountryCaseModel, CountryUpdatesViewHolder>(DiffUtilsCountryWiseCaseModel()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryUpdatesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_my_country_data, parent, false)
        return CountryUpdatesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryUpdatesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CountryUpdatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(countryCaseModel: CountryCaseModel?) {
        val countryName = Locale.getDefault().displayCountry
        itemView.text_view_country_name.text = countryName
        itemView.text_view_confirmed_cases?.text =
            countryCaseModel?.confirmed?.value?.toString()
        itemView.text_view_recovered_cases?.text =
            countryCaseModel?.recovered?.value?.toString()
        itemView.text_view_deceased_cases?.text = countryCaseModel?.deaths?.value?.toString()

        val lastUpdateTime = countryCaseModel?.lastUpdate?.let {
            getFormattedDate(
                it,
                "yyyy-MM-dd'T'HH:mm:ss",
                "MMM d, h:mm a"
            )
        }

        itemView.text_view_last_updated.text = "Last updated: $lastUpdateTime"
    }
}

class DiffUtilsCountryWiseCaseModel : DiffUtil.ItemCallback<CountryCaseModel>() {
    override fun areItemsTheSame(
        oldItem: CountryCaseModel,
        newItem: CountryCaseModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CountryCaseModel,
        newItem: CountryCaseModel
    ): Boolean {
        return oldItem == newItem
    }
}