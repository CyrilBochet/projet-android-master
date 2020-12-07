package com.bochet.cyril.projetandroidcloud2020.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample

@Dao
interface DataDao {

    @Query("SELECT * FROM data_table ORDER BY id ASC")
    fun selectAll(): LiveData<List<ObjectDataSample>>

    @Insert()
    fun insert(ObjectDataSample: ObjectDataSample)

    @Query("DELETE FROM data_table")
    fun deleteAll()
}