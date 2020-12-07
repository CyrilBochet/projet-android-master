package com.bochet.cyril.projetandroidcloud2020

import com.google.gson.GsonBuilder
import com.bochet.cyril.projetandroidcloud2020.endpoint.QuoteEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitQuote: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.tronalddump.io/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getQuote(): QuoteEndpoint = retrofitQuote.create(QuoteEndpoint::class.java)



}

