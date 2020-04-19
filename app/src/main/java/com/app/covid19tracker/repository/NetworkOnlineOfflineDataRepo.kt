package com.app.covid19tracker.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.app.covid19tracker.model.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

@ExperimentalCoroutinesApi
abstract class NetworkOnlineOfflineDataRepo<RESULT, REQUEST> {
    fun asFlow() = flow {
        emit(DataResult.Loading())

        val localData = fetchDataFromLocalDb().first()
        emit(DataResult.Success(localData))

        val apiResponse = fetchDataFromRemoteSource()
        val data = apiResponse.body()

        if (apiResponse.isSuccessful && data != null) {
            saveDataFromRemoteSourceToLocalDb(data)
        } else {
            emit(DataResult.Failure("Something went wrong!"))
        }

        emitAll(fetchDataFromLocalDb().map {
            DataResult.Success(it)
        })
    }

    @WorkerThread
    protected abstract suspend fun saveDataFromRemoteSourceToLocalDb(response: REQUEST)

    @MainThread
    protected abstract fun fetchDataFromLocalDb(): Flow<RESULT>

    @MainThread
    protected abstract suspend fun fetchDataFromRemoteSource(): Response<REQUEST>
}