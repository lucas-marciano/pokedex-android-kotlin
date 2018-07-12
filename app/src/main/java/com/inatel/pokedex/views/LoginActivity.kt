package com.inatel.pokedex.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.inatel.pokedex.R
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun authentication(view: View) {
        startActivity<PokemonsListActivity>()
    }
}
