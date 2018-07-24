package com.lucasmarciano.pokedex.network.services

import com.lucasmarciano.pokedex.model.UserData
import com.lucasmarciano.pokedex.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
interface UsersService{

    @POST("user/authenticate")
    fun authenticate(@Body user: User): Call<UserData>

    @POST("user")
    fun new(@Body users: User): Call<ResponseBody>

    @GET("user/{id}")
    fun getById(@Header("Authorization") token: String, @Path("id") id: Int): Call<User>

    @PUT("user/{id}")
    fun edit(@Header("Authorization") token: String, @Path("id") id: Int, @Body user: User): Call<ResponseBody>

    @DELETE("user/{id}")
    fun delete(@Header("Authorization") token: String, @Path("id") id: Int): Call<ResponseBody>
}