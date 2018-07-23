package com.lucasmarciano.pokedex.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

infix fun Context.builderAlert(body: String): AlertDialog? {
    val builder = AlertDialog.Builder(this)
    builder.setCancelable(false)
    builder.setMessage(body)
    return builder.create()
}

infix fun EditText.isEmpty(string: String): Boolean{
    return this.text.toString().trim() == ""
}