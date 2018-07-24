package com.lucasmarciano.pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * [Description]
 *
 * @project pokedex-android-kotlin
 * @create_at 23/07/2018
 * @author lucasmarciano
 */
data class UserData(
        @SerializedName("data")
        @Expose
        var user: User
)