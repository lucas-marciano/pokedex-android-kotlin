package com.lucasmarciano.pokedex.views.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.views.fragments.AllPokemonsFragment
import com.lucasmarciano.pokedex.views.fragments.MyPokemonsFragment
import com.lucasmarciano.pokedex.views.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        openfragment(MyPokemonsFragment.newInstance(), resources.getString(R.string.title_my_pokemons))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_my_pokemons -> openfragment(MyPokemonsFragment.newInstance(), resources.getString(R.string.title_my_pokemons))
            R.id.nav_all_pokemons -> openfragment(AllPokemonsFragment.newInstance(), resources.getString(R.string.title_all_pokemons))
            R.id.nav_edit_profile -> openfragment(ProfileFragment.newInstance(), resources.getString(R.string.title_edit_my_profile))
            R.id.nav_exit -> logoutAccount()
            else -> drawerLayout.closeDrawer(GravityCompat.START)

        }
        return true
    }

    private fun logoutAccount() {
        toast(getString(R.string.messsage_logout))
        finish()
    }

    private fun openfragment(fragment: Fragment, title: String) {
        supportActionBar!!.title = title
        drawerLayout.closeDrawers()
        val fragmentManeger = supportFragmentManager
        val transaction = fragmentManeger.beginTransaction()
        transaction.replace(R.id.main_root_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
