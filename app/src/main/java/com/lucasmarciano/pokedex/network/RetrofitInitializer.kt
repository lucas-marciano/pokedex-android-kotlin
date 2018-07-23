package com.lucasmarciano.pokedex.network

import com.lucasmarciano.pokedex.network.services.PokemonsService
import com.lucasmarciano.pokedex.network.services.UsersService
import com.lucasmarciano.pokedex.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
class RetrofitInitializer {
    companion object {
        private var retrofit = Retrofit.Builder()
                .baseUrl(Constants.URL_SERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .client(setupOkhttpCliente())
                .build()

        fun setupOkhttpCliente() = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        // Services
        fun pokemonsService() = retrofit.create(PokemonsService::class.java)
        fun userService()     = retrofit.create(UsersService::class.java)
    }
}