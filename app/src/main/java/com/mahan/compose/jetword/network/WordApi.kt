package com.mahan.compose.jetword.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WordApi {

    @GET("all")
    suspend fun getAllWords(): ArrayList<String>


    @GET("word")
    suspend fun getWords(@Query("number") numberOfWords: Int): ArrayList<String>
}