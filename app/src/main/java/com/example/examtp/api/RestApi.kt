package com.example.examtp.api

import retrofit2.Call
import retrofit2.http.*

interface RestApi {

    //@Headers("Content-Type: application/json")
    @GET("v3/f2fab6fd-046a-427e-ac96-8916f6fedeec")
    fun getMovies():Call<List<Movie>>

}