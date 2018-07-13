package com.inatel.pokedex.retrofit.services

import com.inatel.pokedex.model.Pokemons
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
interface PokemonsService{

    @GET("pokemon")
    fun list(): Call<List<Pokemons>>

    @GET("pokemon/{_id}")
    fun getById(@Path("_id") _id: Int): Call<Pokemons>

    @POST("pokemon")
    fun new(@Body pokemons: Pokemons): Call<ResponseBody>

    @PUT("pokemon/{_id}")
    fun edit(@Path("_id") _id: Int, @Body pokemons: Pokemons): Call<ResponseBody>

    @DELETE("pokemon/{_id}")
    fun delete(@Path("_id") _id: Int): Call<ResponseBody>
}