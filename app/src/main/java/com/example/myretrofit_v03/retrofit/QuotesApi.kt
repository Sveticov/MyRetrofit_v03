package com.example.myretrofit_v03.retrofit

import com.example.myretrofit_v03.module.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes(): Response<QuoteList>
}