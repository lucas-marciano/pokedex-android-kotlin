package com.lucasmarciano.pokedex.network.services

import com.lucasmarciano.pokedex.model.PokemonData
import com.lucasmarciano.pokedex.model.Pokemon
import com.lucasmarciano.pokedex.model.PokemonIdData
import com.lucasmarciano.pokedex.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
interface PokemonsService {

    @GET("pokemon")
    fun list(@Header("Authorization") token: String): Call<PokemonData>

    @GET("pokemon/{id}")
    fun getById(@Header("Authorization") token: String, @Path("id") pokeNumber: Int): Call<PokemonIdData>

    @POST("pokemon")
    fun new(@Header("Authorization") token: String, @Body pokemons: Pokemon): Call<ResponseBody>

    @PUT("pokemon/{_id}")
    fun edit(@Header("Authorization") token: String, @Path("_id") _id: Int, @Body pokemons: Pokemon): Call<ResponseBody>

    @DELETE("pokemon/{_id}")
    fun delete(@Header("Authorization") token: String, @Path("_id") _id: Int): Call<ResponseBody>
}