package com.app.covid19tracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.model.GlobalDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCasesData(globalData: GlobalDataModel)

    @Query("Select * from global_data")
    fun getAllCasesData(): Flow<GlobalDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountryWiseData(countryCaseModel: CountryCaseModel)

    @Query("Select * from country_data")
    fun getCountryWiseData(): Flow<CountryCaseModel>

    @Query("DELETE FROM daily_data")
    fun deleteDailyData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyData(countryCaseModel: List<DailyDataModel.DailyDataModelItem>)

    @Query("Select * from daily_data")
    fun getDailyData(): Flow<List<DailyDataModel.DailyDataModelItem>>
}