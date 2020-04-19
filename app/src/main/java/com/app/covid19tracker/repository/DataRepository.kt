package com.app.covid19tracker.repository

import com.app.covid19tracker.db.DatabaseDao
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.model.DataResult
import com.app.covid19tracker.model.GlobalDataModel
import com.app.covid19tracker.networking.ApiService
import com.app.covid19tracker.networking.NetworkOnlineOfflineDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@ExperimentalCoroutinesApi
class DataRepository(private val apiService: ApiService, private val databaseDao: DatabaseDao) {
    suspend fun getRawData(): Flow<DataResult<GlobalDataModel>> {
        return object : NetworkOnlineOfflineDataRepo<GlobalDataModel, GlobalDataModel>() {
            override suspend fun saveDataFromRemoteSourceToLocalDb(response: GlobalDataModel) {
                databaseDao.insertAllCasesData(response)
            }

            override fun fetchDataFromLocalDb(): Flow<GlobalDataModel> {
                return databaseDao.getAllCasesData()
            }

            override suspend fun fetchDataFromRemoteSource(): Response<GlobalDataModel> {
                return apiService.getGlobalData()
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }

    suspend fun getCountryWiseData(countryIso: String): Flow<DataResult<CountryCaseModel>> {
        return object : NetworkOnlineOfflineDataRepo<CountryCaseModel, CountryCaseModel>() {
            override suspend fun saveDataFromRemoteSourceToLocalDb(response: CountryCaseModel) {
                databaseDao.insertCountryWiseData(response)
            }

            override fun fetchDataFromLocalDb(): Flow<CountryCaseModel> {
                return databaseDao.getCountryWiseData()
            }

            override suspend fun fetchDataFromRemoteSource(): Response<CountryCaseModel> {
                return apiService.getCountryWiseData(countryIso)
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }

    suspend fun getDailyData(): Flow<DataResult<List<DailyDataModel.DailyDataModelItem>>> {
        return object : NetworkOnlineOfflineDataRepo<List<DailyDataModel.DailyDataModelItem>, List<DailyDataModel.DailyDataModelItem>>() {
            override suspend fun saveDataFromRemoteSourceToLocalDb(response: List<DailyDataModel.DailyDataModelItem>) {
                databaseDao.deleteDailyData()
                databaseDao.insertDailyData(response)
            }

            override fun fetchDataFromLocalDb(): Flow<List<DailyDataModel.DailyDataModelItem>> {
                return databaseDao.getDailyData()
            }

            override suspend fun fetchDataFromRemoteSource(): Response<List<DailyDataModel.DailyDataModelItem>> {
                return apiService.getDailyData()
            }
        }.asFlow().flowOn(Dispatchers.IO)
    }
}