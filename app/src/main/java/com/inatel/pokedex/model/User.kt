package com.inatel.pokedex.model

/**
 * [Description]
 *
 * @project pokedex-android-kotlin
 * @create_at 10/07/2018
 * @author lucasmarciano
 */
data class User(
        val name: String,
        val firstName: String,
        val lastName: String,
        val username: String,
        val email: String,
        val password: String,
        val role: String = "USER"
)