package com.app.covid19tracker.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.covid19tracker.utility.Constants.ALL_DATA_MODEL
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = ALL_DATA_MODEL)
data class GlobalDataModel(
    @PrimaryKey
    var id: Int? = 1,
    val confirmed: CasesValueDataModel?,
    val countries: String?,
    val countryDetail: CasesValueDataModel?,
    val dailySummary: String?,
    val dailyTimeSeries: CasesValueDataModel?,
    val deaths: CasesValueDataModel?,
    val image: String?,
    val lastUpdate: String?,
    val recovered: CasesValueDataModel?,
    val source: String?
) : Parcelable