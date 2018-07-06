package com.inatel.pokedex.retrofit

import com.inatel.pokedex.retrofit.services.PokemonsService
import com.inatel.pokedex.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_SERVIDOR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun pokemonsService() = retrofit.create(PokemonsService::class.java)
}