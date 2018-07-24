package com.lucasmarciano.pokedex.model


/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
data class Pokemon(
        var pokeNumber: Int,
        var name: String,
        var weight: Float,
        var picture: String,
        var stats: Stats,
        var types: List<String>
){
    override fun toString(): String {
        var result = ""
        for (item in types) {
            result = "$result$item "
        }
        return result
    }
}