package com.bochet.cyril.projetandroidcloud2020.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample
import com.bochet.cyril.projetandroidcloud2020.model.Quote
import com.bochet.cyril.projetandroidcloud2020.repository.DataRepository

import com.bochet.cyril.projetandroidcloud2020.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

    private val mDataRepository: DataRepository by lazy { DataRepository() }
    var mDataLiveData: LiveData<List<ObjectDataSample>> = mDataRepository.selectAllQuotes()

    fun fetchNewQuote(data:ObjectDataSample) {
        viewModelScope.launch(Dispatchers.IO) {
            mDataRepository.fetchData(data)
        }
    }

    fun deleteAllQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            mDataRepository.deleteAllQuotes()
        }
    }
}