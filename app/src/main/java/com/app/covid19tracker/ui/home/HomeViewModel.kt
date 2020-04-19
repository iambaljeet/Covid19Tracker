package com.app.covid19tracker.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covid19tracker.model.CountryCaseModel
import com.app.covid19tracker.model.DataResult
import com.app.covid19tracker.model.GlobalDataModel
import com.app.covid19tracker.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@ExperimentalCoroutinesApi
class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getRawData(): MutableLiveData<DataResult<GlobalDataModel?>> {
        val dataLiveData = MutableLiveData<DataResult<GlobalDataModel?>>()
        viewModelScope.launch {
            val response = dataRepository.getRawData()
            response.collect {
                dataLiveData.postValue(it)
            }
        }
        return dataLiveData
    }

    fun getCountryWiseData(countryIso: String): MutableLiveData<DataResult<CountryCaseModel?>> {
        val dataLiveData = MutableLiveData<DataResult<CountryCaseModel?>>()
        dataLiveData.postValue(DataResult.Loading())
        viewModelScope.launch {
            val response = dataRepository.getCountryWiseData(countryIso)
            withContext(Dispatchers.Main) {
                response.collect {
                    dataLiveData.postValue(it)
                }
            }
        }
        return dataLiveData
    }
}