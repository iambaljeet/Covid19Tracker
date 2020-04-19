package com.app.covid19tracker.networking

import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.model.GlobalDataModel
import com.app.covid19tracker.utility.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(Constants.BASIC_DATA)
    suspend fun getGlobalData(): Response<GlobalDataModel>

    @GET(Constants.COUNTRIES + "/{iso}")
    suspend fun getCountryWiseData(@Path("iso") countryIso: String): Response<CountryCaseModel>

    @GET(Constants.DAILY)
    suspend fun getDailyData(): Response<List<DailyDataModel.DailyDataModelItem>>
}