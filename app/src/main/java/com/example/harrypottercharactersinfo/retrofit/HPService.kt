package com.example.harrypottercharactersinfo.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object HPService {

    private val retrofit by lazy(LazyThreadSafetyMode.NONE) { provideRetrofit() }
    val hpApi by lazy(LazyThreadSafetyMode.NONE) {
        retrofit.create<HPApi>()
    }

    private fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("http://hp-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}