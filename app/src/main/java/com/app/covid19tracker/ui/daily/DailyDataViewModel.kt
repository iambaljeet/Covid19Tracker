package com.app.covid19tracker.ui.daily

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covid19tracker.model.DailyDataModel
import com.app.covid19tracker.model.DataResult
import com.app.covid19tracker.repository.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DailyDataViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getDailyData(): MutableLiveData<DataResult<List<DailyDataModel.DailyDataModelItem>?>> {
        val dataLiveData = MutableLiveData<DataResult<List<DailyDataModel.DailyDataModelItem>?>>()
        viewModelScope.launch {
            val response = dataRepository.getDailyData()
            response.collect {
                dataLiveData.postValue(it)
            }
        }
        return dataLiveData
    }
}