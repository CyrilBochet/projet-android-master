package com.bochet.cyril.projetandroidcloud2020.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bochet.cyril.projetandroidcloud2020.model.Quote

import com.bochet.cyril.projetandroidcloud2020.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val mQuoteRepository: QuoteRepository by lazy { QuoteRepository() }
    var mQuoteLiveData: LiveData<List<Quote>> = mQuoteRepository.selectAllQuotes()

    fun fetchNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            mQuoteRepository.fetchData()
        }
    }

    fun deleteAllQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            mQuoteRepository.deleteAllQuotes()
        }
    }
}