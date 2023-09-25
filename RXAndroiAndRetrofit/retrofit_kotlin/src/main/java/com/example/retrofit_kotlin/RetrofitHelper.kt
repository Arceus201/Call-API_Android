package com.example.retrofit_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val basrUrl = "https://reqres.in/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(basrUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}