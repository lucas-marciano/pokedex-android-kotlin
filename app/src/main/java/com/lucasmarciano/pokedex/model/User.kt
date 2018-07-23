package com.lucasmarciano.pokedex.model

/**
 * [Description]
 *
 * @project pokedex-android-kotlin
 * @create_at 10/07/2018
 * @author lucasmarciano
 */
data class User(
        var username: String = "",
        var password: String = "",
        var name: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var role: String = "USER",
        var _id: Int? = null,
        var token: String = "",
        var rtoken: String = ""
)