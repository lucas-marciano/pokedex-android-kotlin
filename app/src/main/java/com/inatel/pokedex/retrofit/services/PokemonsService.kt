package com.inatel.pokedex.retrofit.services

import com.inatel.pokedex.model.Pokemons
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
interface PokemonsService{

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @GET("pokemon")
    fun list(): Call<List<Pokemons>>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @GET("pokemon/{_id}")
    fun getById(@Path("_id") _id: Int): Call<Pokemons>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @POST("pokemon")
    fun new(@Body pokemons: Pokemons): Call<ResponseBody>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @PUT("pokemon/{_id}")
    fun edit(@Path("_id") _id: Int, @Body pokemons: Pokemons): Call<ResponseBody>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @DELETE("pokemon/{_id}")
    fun delete(@Path("_id") _id: Int): Call<ResponseBody>
}