package com.bochet.cyril.projetandroidcloud2020.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "quote_table")
data class Quote(
    @Expose
    @SerializedName("value")
    val quote: String,
    @Expose
    @SerializedName("appeared_at")
    val appeared_at: String,
    @Expose
    @SerializedName("created_at")
    val created_at: String,
    @Expose
    @SerializedName("updated_at")
    val updated_at: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
