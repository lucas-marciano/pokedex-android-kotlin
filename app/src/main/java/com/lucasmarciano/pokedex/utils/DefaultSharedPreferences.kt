package com.lucasmarciano.pokedex.utils

import android.content.Context

/**
 * [Description]
 *
 * @project pokedex-android-kotlin
 * @create_at 23/07/2018
 * @author lucasmarciano
 */
class DefaultSharedPreferences {
    companion object {
        val SP_KEY_TOKE = "sharedPreferenceTokenKey"

        fun setTokenAuthentication(context: Context, token: String) {

        }

        fun getTokenAuthentication(context: Context): String {
            return ""
        }

        fun clearAllSharedPreferences(context: Context) {

        }
    }
}