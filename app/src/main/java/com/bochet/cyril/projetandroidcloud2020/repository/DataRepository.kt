package com.bochet.cyril.projetandroidcloud2020.repository
import androidx.lifecycle.LiveData
import com.bochet.cyril.projetandroidcloud2020.architecture.CustomApplication
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository {

    private val mDataDao = CustomApplication.instance.mApplicationDatabase.mDataDao()



    fun selectAllQuotes(): LiveData<List<ObjectDataSample>> {
        return mDataDao.selectAll()
    }

    private suspend fun insertQuote(Data: ObjectDataSample) = withContext(Dispatchers.IO) {
        mDataDao.insert(Data)
    }

    suspend fun deleteAllQuotes() = withContext(Dispatchers.IO) {
        mDataDao.deleteAll()
    }

    suspend fun fetchData(data:ObjectDataSample) {

        insertQuote(data)
    }




}