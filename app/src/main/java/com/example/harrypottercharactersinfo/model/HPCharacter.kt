package com.example.harrypottercharactersinfo.model

import com.google.gson.annotations.SerializedName

data class HPCharacter(
    val name: String,
    @SerializedName("image")
    val imageUrl: String
)