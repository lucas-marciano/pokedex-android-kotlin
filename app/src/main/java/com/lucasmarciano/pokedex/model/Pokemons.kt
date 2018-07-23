package com.lucasmarciano.pokedex.model


/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
data class Pokemons(
        var _id: Int,
        var pokeNumber: Int,
        var name: String,
        var weight: Float,
        var picture: String,
        var stats: Stats,
        var types: List<String>

)