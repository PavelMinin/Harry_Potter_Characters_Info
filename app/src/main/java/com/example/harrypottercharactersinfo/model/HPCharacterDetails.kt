package com.example.harrypottercharactersinfo.model

import com.google.gson.annotations.SerializedName

data class HPCharacterDetails(
        val name: String,
        @SerializedName("alternative_names")
        val alternativeNames: List<String>,
        val species: String,
        val gender: String,
        val house: String,
        val dateOfBirth: String,
        val wizard: Boolean,
        val ancestry: String,
        val eyeColour: String,
        val hairColour: String,
//        val wand: Wand,
        val patronus: String,
        val hogwartsStudent: Boolean,
        val hogwartsStaff: Boolean,
        val actor: String,
        val alternate_actors: List<String>,
        val alive: Boolean,
        @SerializedName("image")
        val imageUrl: String
        )

//data class Wand (
//        val wood: String,
//        val core: String,
//        val length: Double
//        )