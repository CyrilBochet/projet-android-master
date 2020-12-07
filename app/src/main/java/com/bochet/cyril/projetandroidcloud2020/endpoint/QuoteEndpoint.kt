package com.bochet.cyril.projetandroidcloud2020.endpoint

import com.bochet.cyril.projetandroidcloud2020.model.Quote
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuoteEndpoint {

    @Headers(
        "Accept: application/json",
        "Accept: */*"
    )
    @GET("random/quote")
    suspend fun getRandomQuote(): Quote
}