package com.bochet.cyril.projetandroidcloud2020

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bochet.cyril.projetandroidcloud2020.dao.DataDao
import com.bochet.cyril.projetandroidcloud2020.dao.QuoteDao
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample
import com.bochet.cyril.projetandroidcloud2020.model.Quote

@Database(
    entities = [
        Quote::class,
        ObjectDataSample::class
    ],
    version = 14,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {

    abstract fun mQuoteDao(): QuoteDao


    abstract fun mDataDao(): DataDao
}
