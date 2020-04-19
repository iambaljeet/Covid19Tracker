package com.app.covid19tracker.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.covid19tracker.utility.Constants
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = Constants.COUNTRY_WISE_DATA)
data class CountryCaseModel(
    @PrimaryKey
    var id: Int? = 1,
    val confirmed: CasesValueDataModel?,
    val deaths: CasesValueDataModel?,
    val lastUpdate: String?,
    val recovered: CasesValueDataModel?
) : Parcelable