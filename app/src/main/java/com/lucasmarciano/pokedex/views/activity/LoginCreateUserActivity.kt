package com.lucasmarciano.pokedex.views.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.views.fragments.LoginFragment

class LoginCreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_create_user)

        openfragment(LoginFragment.newInstance())
    }

    private fun openfragment(fragment: Fragment) {
        val fragmentManeger = supportFragmentManager
        val transaction = fragmentManeger.beginTransaction()
        transaction.replace(R.id.root_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            openfragment(LoginFragment.newInstance())
        }

        return super.onOptionsItemSelected(item)

    }
}
