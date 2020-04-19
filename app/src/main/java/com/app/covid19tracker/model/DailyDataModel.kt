package com.app.covid19tracker.model


import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.covid19tracker.utility.Constants
import kotlinx.android.parcel.Parcelize

class DailyDataModel : ArrayList<DailyDataModel.DailyDataModelItem>(){
    @SuppressLint("ParcelCreator")
    @Parcelize
    @Entity(tableName = Constants.DAILY_DATA)
    data class DailyDataModelItem(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        val active: Int?,
        val confirmed: Confirmed?,
        val deaths: Deaths?,
        val deltaConfirmed: Int?,
        val deltaConfirmedDetail: DeltaConfirmedDetail?,
        val deltaRecovered: Int?,
        val incidentRate: Double?,
        val mainlandChina: Int?,
        val otherLocations: Int?,
        val peopleTested: Int?,
        val recovered: Recovered?,
        val reportDate: String?,
        val totalConfirmed: Int?,
        val totalRecovered: Int?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Confirmed(
            val china: Int?,
            val outsideChina: Int?,
            val total: Int?
        ) : Parcelable
    
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Deaths(
            val china: Int?,
            val outsideChina: Int?,
            val total: Int?
        ) : Parcelable
    
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class DeltaConfirmedDetail(
            val china: Int?,
            val outsideChina: Int?,
            val total: Int?
        ) : Parcelable
    
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Recovered(
            val china: Int?,
            val outsideChina: Int?,
            val total: Int?
        ) : Parcelable
    }
}