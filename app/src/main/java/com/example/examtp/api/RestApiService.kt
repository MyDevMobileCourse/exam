package com.example.examtp.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class RestApiService {

    fun getMovies(onResult: (List<Movie>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getMovies().enqueue(
            object : Callback<List<Movie>> {
                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    println(t.message);
                    println(t);
                    onResult(null)
                }
                override fun onResponse( call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    val addedUser = response.body()
                    println(response);
                    onResult(addedUser)
                }
            }
        )
    }

}