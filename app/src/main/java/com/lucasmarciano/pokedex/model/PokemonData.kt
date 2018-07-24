package com.lucasmarciano.pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * [Description]
 *
 * @project pokedex-android-kotlin
 * @create_at 24/07/2018
 * @author lucasmarciano
 */
data class PokemonData(
        @SerializedName("data")
        @Expose
        var pokemons: List<Pokemon>
)