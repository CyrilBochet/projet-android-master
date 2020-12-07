package com.bochet.cyril.projetandroidcloud2020.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bochet.cyril.projetandroidcloud2020.architecture.CustomApplication
import com.bochet.cyril.projetandroidcloud2020.RetrofitBuilder
import com.bochet.cyril.projetandroidcloud2020.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository {

    private val mQuoteDao = CustomApplication.instance.mApplicationDatabase.mQuoteDao()

    fun selectAllQuotes() : LiveData<List<Quote>> {
        return mQuoteDao.selectAll()
    }

    private suspend fun insertQuote(Quote: Quote) = withContext(Dispatchers.IO) {
        mQuoteDao.insert(Quote)
    }

    suspend fun deleteAllQuotes() = withContext(Dispatchers.IO) {
        mQuoteDao.deleteAll()
    }

    suspend fun fetchData() {
        insertQuote(RetrofitBuilder.getQuote().getRandomQuote())
    }
}