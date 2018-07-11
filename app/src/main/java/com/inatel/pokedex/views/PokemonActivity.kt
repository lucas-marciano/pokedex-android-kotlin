package com.inatel.pokedex.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.inatel.pokedex.R
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
    }
}
