package com.bochet.cyril.projetandroidcloud2020.architecture

import android.app.Application
import androidx.room.Room
import com.bochet.cyril.projetandroidcloud2020.CustomRoomDatabase

class CustomApplication : Application() {

    ////////////////////////////////////////////
    // Attribute ///////////////////////////////
    ////////////////////////////////////////////

    companion object {
        lateinit var instance: CustomApplication
    }

    val mApplicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "QuotesDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    ////////////////////////////////////////////
    // Managing Lifecycle //////////////////////
    ////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
