package com.lucasmarciano.pokedex.views.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.model.Pokemon
import com.lucasmarciano.pokedex.model.PokemonData
import com.lucasmarciano.pokedex.model.PokemonIdData
import com.lucasmarciano.pokedex.network.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private var mToken = ""
    private var mPokemonNumber = 1
    private var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mToken = intent.getStringExtra("token")
        mPokemonNumber = intent.getIntExtra("pokeNumber", 1)
        val name = intent.getStringExtra("name")

        setSupportActionBar(toolbarDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = name.capitalize()
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        val call = RetrofitInitializer.pokemonsService().getById("Bearer $mToken", mPokemonNumber)
        call.enqueue(object : Callback<PokemonIdData?> {

            override fun onResponse(call: Call<PokemonIdData?>?, response: Response<PokemonIdData?>?) {
                response?.body().let {
                    updateLayout(it!!.pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonIdData?>?, t: Throwable?) {
                Log.e("DetailActivity", t.toString())
                toast(resources.getString(R.string.message_error_connection))
                context.finish()
            }
        })
    }

    private fun updateLayout(pokemon: Pokemon) {
        Glide.with(context).load(pokemon.picture).into(ivPicture)
        tvName.text = pokemon.name
        tvTypes.text = pokemon.toString()
        tvSpeed.text = pokemon.stats.speed.toString()
        tvAttack.text = pokemon.stats.attack.toString()
        tvDefense.text = pokemon.stats.defense.toString()
        tvHp.text = pokemon.stats.hp.toString()
        tvSDefense.text = pokemon.stats.specialDefense.toString()
        tvSAttack.text = pokemon.stats.specialAttack.toString()

    }
}
