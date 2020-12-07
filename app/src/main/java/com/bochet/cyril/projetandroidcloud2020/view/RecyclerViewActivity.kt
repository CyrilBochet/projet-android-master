package com.bochet.cyril.projetandroidcloud2020.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.model.ObjectDataSample
import com.bochet.cyril.projetandroidcloud2020.view.adapter.DataAdapter
import com.bochet.cyril.projetandroidcloud2020.viewmodel.DataViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.item_custom_recycler.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var mAdapter: DataAdapter

    private lateinit var mViewModel: DataViewModel
    private var location: Location? = null;

    private var mObserverData = Observer<List<ObjectDataSample>> {
        updateRecyclerView(ArrayList(it))
    }
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val PERMISSION_ID = 1010

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        // Create the instance of adapter
        mAdapter = DataAdapter(this)
        // We define the style
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // We set the adapter to recycler view
        recyclerView.adapter = mAdapter

        buttonAddItem.setOnClickListener { addRandomQuote() }
        buttonDeleteItem.setOnClickListener { deleteRandomQuote() }
        

    }

    override fun onStart() {
        super.onStart()
        mViewModel.mDataLiveData.observe(this, mObserverData)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStop() {
        mViewModel.mDataLiveData.removeObserver(mObserverData)
        super.onStop()
    }

    private fun updateRecyclerView(newList: ArrayList<ObjectDataSample>) {
        mAdapter.rebuild(newList)
    }

    private fun addRandomQuote() {
        var data = generateDataObject()

        if (data != null && data.latitude != "") {
            mViewModel.fetchNewQuote(data)
        } else {
            val toast = Toast.makeText(
                applicationContext,
                "Impossible de récupérer votre localisation, merci de rééssayer.",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }


    }

    private fun deleteRandomQuote() {
        mViewModel.deleteAllQuotes()
    }

    fun CheckPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false

    }

    fun RequestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    fun generateDataObject(): ObjectDataSample? {
        if (getLastLocation()) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(formatter)
            return ObjectDataSample(
                location?.longitude.toString(),
                location?.latitude.toString(),
                formatted
            )
        }

        return null
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(): Boolean {

        if (CheckPermission()) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                location = task.result
            }
            if (location != null) {
                return true;
            }

        } else {
            RequestPermission()
            Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT).show()

        }
        return false

    }
}
