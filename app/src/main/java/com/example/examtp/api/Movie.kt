package com.example.examtp.api

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("nomfilm")
    val nomfilm: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("duree")
    val duree: String,
)
