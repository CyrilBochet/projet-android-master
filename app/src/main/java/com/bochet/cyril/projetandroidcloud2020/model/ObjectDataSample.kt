package com.bochet.cyril.projetandroidcloud2020.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data_table")
data class ObjectDataSample(
    @Expose
    @SerializedName("longitude")
    var longitude: String,
    @Expose
    @SerializedName("latitude")
    var latitude: String,
    @Expose
    @SerializedName("date")
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}