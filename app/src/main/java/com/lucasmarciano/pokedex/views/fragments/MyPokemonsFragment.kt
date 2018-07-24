package com.lucasmarciano.pokedex.views.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.adapter.PokemonsListAdapter
import com.lucasmarciano.pokedex.model.Pokemon
import com.lucasmarciano.pokedex.model.PokemonData
import com.lucasmarciano.pokedex.network.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_my_pokemons.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class MyPokemonsFragment : Fragment() {
    private var pokemons: List<Pokemon>? = null
    private var mToken: String = ""

    companion object {

        fun newInstance(): MyPokemonsFragment {
            return MyPokemonsFragment()
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mToken = arguments!!.getString("token")
        return inflater.inflate(R.layout.fragment_my_pokemons, container, false)
    }

    override fun onStart() {
        super.onStart()
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        val call = RetrofitInitializer.pokemonsService().list("Bearer $mToken")
        call.enqueue(object : Callback<PokemonData?> {

            override fun onResponse(call: Call<PokemonData?>?, response: Response<PokemonData?>?) {
                if (response?.body() != null) {
                    pokemons = response.body()!!.pokemons
                    updateView()
                    Log.d("MyPokemonsFragment", "Deu certo")
                } else {
                    updateView(false)
                    Log.e("MyPokemonsFragment", "Body null :/")
                }
            }

            override fun onFailure(call: Call<PokemonData?>?, t: Throwable?) {
                Log.e("MyPokemonsFragment", t.toString())
                updateView(false)
            }
        })
    }

    private fun updateView(itsWork: Boolean = true) {
        if (itsWork) {
            progress_bar.visibility = View.GONE
            text_error.visibility = View.GONE
            floatButton.visibility = View.VISIBLE
            pokeListRecyclerview.visibility = View.VISIBLE

            setupRecicleView()
        } else {
            progress_bar.visibility = View.GONE
            pokeListRecyclerview.visibility = View.GONE
            floatButton.visibility = View.VISIBLE
            text_error.visibility = View.VISIBLE
            text_error.text = resources.getString(R.string.message_error_connection)
        }
    }

    private fun setupRecicleView() {
        pokemons?.let {
            pokeListRecyclerview.adapter = PokemonsListAdapter(it, context, mToken)
            val layoutManager = StaggeredGridLayoutManager(
                    resources.getInteger(R.integer.num_coluns_recycleview),
                    StaggeredGridLayoutManager.VERTICAL)
            pokeListRecyclerview.layoutManager = layoutManager
        }

    }


}
