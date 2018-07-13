package com.inatel.pokedex.utils

import android.app.AlertDialog
import android.content.Context

/**
 *
 * Creation 12/07/2018
 * @author lucasmarciano
 * @version 1.0.0
 */
class Utils {
    companion object {
        fun builAlertDialogNoAction(context:Context, body: String, canClose: Boolean = true, title: String = ""): AlertDialog? {
            val builder = AlertDialog.Builder(context)
            if(!title.isEmpty())
                builder.setTitle(body)

            builder.setCancelable(canClose)
            builder.setMessage(body)
            return builder.create()
        }
    }
}