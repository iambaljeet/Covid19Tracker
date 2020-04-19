package com.app.covid19tracker.db

import androidx.room.TypeConverter
import com.app.covid19tracker.model.CasesValueDataModel
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.utility.fromJson
import com.app.covid19tracker.utility.toJson

class DataTypeConverter {
    @TypeConverter
    fun toCasesValueDataModel(value: String): CasesValueDataModel = fromJson(value)

    @TypeConverter
    fun fromCasesValueDataModel(value: CasesValueDataModel): String = toJson(value)

    @TypeConverter
    fun toConfirmed(value: String): DailyDataModel.DailyDataModelItem.Confirmed = fromJson(value)

    @TypeConverter
    fun fromConfirmed(value: DailyDataModel.DailyDataModelItem.Confirmed): String = toJson(value)

    @TypeConverter
    fun toDeaths(value: String): DailyDataModel.DailyDataModelItem.Deaths = fromJson(value)

    @TypeConverter
    fun fromDeaths(value: DailyDataModel.DailyDataModelItem.Deaths): String = toJson(value)

    @TypeConverter
    fun toDeltaConfirmedDetail(value: String): DailyDataModel.DailyDataModelItem.DeltaConfirmedDetail = fromJson(value)

    @TypeConverter
    fun fromDeltaConfirmedDetail(value: DailyDataModel.DailyDataModelItem.DeltaConfirmedDetail): String = toJson(value)

    @TypeConverter
    fun toRecovered(value: String): DailyDataModel.DailyDataModelItem.Recovered = fromJson(value)

    @TypeConverter
    fun fromRecovered(value: DailyDataModel.DailyDataModelItem.Recovered): String = toJson(value)
}