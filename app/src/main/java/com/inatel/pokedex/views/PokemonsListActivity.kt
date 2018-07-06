package com.inatel.pokedex.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.inatel.pokedex.R
import com.inatel.pokedex.adapter.PokemonsListAdapter
import com.inatel.pokedex.model.Pokemons
import com.inatel.pokedex.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_poke_list.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonsListActivity : AppCompatActivity() {
    var pokemons: List<Pokemons>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke_list)

        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        val call = RetrofitInitializer().pokemonsService().list()
        call.enqueue(object: Callback<List<Pokemons>?> {
            override fun onResponse(call: Call<List<Pokemons>?>?, response: Response<List<Pokemons>?>?) {
                response?.body()?.let {
                    pokemons = it
                    progress_bar.visibility = View.GONE
                    floatButton.visibility = View.VISIBLE
                    setupRecicleView()
                }
            }

            override fun onFailure(call: Call<List<Pokemons>?>?, t: Throwable?) {
                error(t?.message!!)
            }
        })
    }

    private fun setupRecicleView() {
        pokeListRecyclerview.visibility = View.VISIBLE
        pokemons?.let {
            pokeListRecyclerview.adapter = PokemonsListAdapter(it, this)
            val layoutManager = StaggeredGridLayoutManager(
                    resources.getInteger(R.integer.num_coluns_recycleview),
                    StaggeredGridLayoutManager.VERTICAL)
            pokeListRecyclerview.layoutManager = layoutManager
        }

    }

    fun catchPokemon(view: View) {
        startActivity<CapturePokemonActivity>()
    }
}