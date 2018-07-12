package com.inatel.pokedex.model

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
data class Pokemons(
        val id: Int,
        val pokeNumber: Int,
        val name: String,
        val weight: Float,
        val picture: String,
        val stats: Map<String, Int>,
        val types: List<String>

)