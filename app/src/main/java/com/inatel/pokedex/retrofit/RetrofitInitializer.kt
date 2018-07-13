package com.inatel.pokedex.retrofit

import com.inatel.pokedex.retrofit.services.PokemonsService
import com.inatel.pokedex.retrofit.services.UsersService
import com.inatel.pokedex.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Lucas Marciano on 27/04/2018.
 */
class RetrofitInitializer {

    fun teste(){
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "Your-App-Name")
                        .header("Accept", "application/vnd.yourapi.v1.full+json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        })
    }

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