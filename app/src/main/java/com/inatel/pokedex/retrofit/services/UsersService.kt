package com.inatel.pokedex.retrofit.services

import com.inatel.pokedex.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
interface UsersService{
    @Headers("Content-Type: application/json")
    @POST("user/authenticate")
    fun authenticate(@Path("username") username: String, @Path("password") password: String): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("user")
    fun new(@Body users: User): Call<ResponseBody>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @GET("user/{id}")
    fun getById(@Path("id") id: Int): Call<User>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @PUT("user/{id}")
    fun edit(@Path("id") id: Int, @Body user: User): Call<ResponseBody>

    @Headers("Content-Type: application/json",
            "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjQ1NGRlZTczMmQ2MzY5ZDkxYWFjMmIiLCJyb2xlIjoiQURNSU4iLCJfY29tcGFueSI6bnVsbCwiaWF0IjoxNTMxMjY4NjE2LCJleHAiOjE2MTc1ODIyMTZ9.99lrkP1LRSqwR_E6oM9zDzXUEXRU3vw1W67jglag1Vg"
    )
    @DELETE("user/{id}")
    fun delete(@Path("id") id: Int): Call<ResponseBody>
}