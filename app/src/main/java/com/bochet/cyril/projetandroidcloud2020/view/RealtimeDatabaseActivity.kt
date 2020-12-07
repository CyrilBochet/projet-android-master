package com.bochet.cyril.projetandroidcloud2020.view


import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bochet.cyril.projetandroidcloud2020.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_realtime_database.*
import java.time.LocalDateTime
import java.util.*


class RealtimeDatabaseActivity : AppCompatActivity() {



    val database = Firebase.database
    val batteryLevelRef = database.getReference("battery/level")
    val batteryStatusRef = database.getReference("battery/status")
    val batteryDateMesureRef = database.getReference("battery/date")
    val ifilterBattery = IntentFilter(Intent.ACTION_BATTERY_CHANGED)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtime_database)

        btnInsertDB.setOnClickListener {
            sendNewValue()
        }


        batteryLevelRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<String>()
                BatteryLevelTxt.text = "Niveau de batterie : ${value} %"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                BatteryLevelTxt.text = "Failed to read value."
            }
        })
        batteryStatusRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<String>()
                BatteryStatusTxt.text = "${value}"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                BatteryStatusTxt.text = "Failed to read value."
            }
        })
    }
    private fun sendNewValue() {
        val batteryLevel: Int =  getBatteryLevel()
        val batteryStatus: String =  getBatteryStatus()

        batteryDateMesureRef.setValue(LocalDateTime.now())
        batteryLevelRef.setValue("$batteryLevel")
        batteryStatusRef.setValue("$batteryStatus")
    }

    private fun getBatteryLevel(): Int {

        val batteryStatus = registerReceiver(null, ifilterBattery)
        val level = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPct = level / scale.toFloat()
        return (batteryPct * 100).toInt()
    }
    private fun getBatteryStatus(): String {
        val batteryStatus = registerReceiver(null, ifilterBattery)
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)!!

        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        if (isCharging){
            return "Charging"
        }
        return "Not charging"
    }




}