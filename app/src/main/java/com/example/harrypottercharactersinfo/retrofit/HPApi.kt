package com.example.harrypottercharactersinfo.retrofit

import com.example.harrypottercharactersinfo.model.HPCharacter
import retrofit2.Call
import retrofit2.http.GET

interface HPApi {

    @GET("characters")
    fun getCharacters():
            Call<List<HPCharacter>>
}