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

    @GET("posts/{id}/show")
    fun getById(@Path("id") id: Int): Call<Pokemons>

    @POST("pokemon/new")
    fun new(@Body pokemons: Pokemons): Call<ResponseBody>

    @PUT("pokemon/{id}/edit")
    fun edit(@Path("id") id: Int, @Body pokemons: Pokemons): Call<ResponseBody>

    @DELETE("pokemon/{id}/delete")
    fun delete(@Path("id") id: Int): Call<ResponseBody>
}