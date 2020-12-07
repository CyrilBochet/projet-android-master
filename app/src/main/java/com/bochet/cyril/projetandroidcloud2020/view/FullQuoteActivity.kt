package com.bochet.cyril.projetandroidcloud2020.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bochet.cyril.projetandroidcloud2020.R
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_full_quote.*


class FullQuoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_quote)

        val full_quote = intent.getStringExtra("full_quote");

        textViewFullQuote.text = full_quote;

    }


}