package com.bochet.cyril.projetandroidcloud2020.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bochet.cyril.projetandroidcloud2020.model.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ORDER BY id ASC")
    fun selectAll(): LiveData<List<Quote>>

    @Insert()
    fun insert(Quote: Quote)

    @Query("DELETE FROM quote_table")
    fun deleteAll()
}