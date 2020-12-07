package com.bochet.cyril.projetandroidcloud2020.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bochet.cyril.projetandroidcloud2020.R
import com.bochet.cyril.projetandroidcloud2020.firebase.view.FirebaseLoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainButtonRetrofit.setOnClickListener { goToRetrofit() }
        mainButtonRecyclerView.setOnClickListener { goToRecyclerView() }
        mainButtonActivityRealtimeDB.setOnClickListener { goToRealtimeDatabase() }
        mainButtonActivityFirebase.setOnClickListener { goToFirebase() }
    }

    private fun goToRetrofit() {
        startActivity(Intent(this, RetrofitActivity::class.java))
    }
    private fun goToRecyclerView() {
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }

    private fun goToRealtimeDatabase() {
        startActivity(Intent(this, RealtimeDatabaseActivity::class.java))
    }

    private fun goToFirebase() {
        startActivity(Intent(this, FirebaseLoginActivity::class.java))
    }
}