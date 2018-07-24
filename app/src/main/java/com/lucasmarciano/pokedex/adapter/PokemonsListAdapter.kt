package com.lucasmarciano.pokedex.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lucasmarciano.pokedex.R
import com.lucasmarciano.pokedex.model.Pokemon
import com.lucasmarciano.pokedex.views.activity.DetailActivity
import kotlinx.android.synthetic.main.list_item.view.*

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
class PokemonsListAdapter(
        val pokemons: List<Pokemon>,
        val mContext: Context?,
        val token: String = "") : Adapter<PokemonsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = pokemons[position]
        holder.bindView(post)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(pokemon: Pokemon) {

            val name = itemView.tvNamePokemon
            val tipo = itemView.tvTipo

            name.text = pokemon.name.capitalize()
            tipo.text = pokemon.toString()
            Glide.with(mContext!!).load(pokemon.picture).into(itemView.ivPokemon)

            itemView.setOnClickListener {
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra("pokeNumber", pokemon.pokeNumber)
                intent.putExtra("token", token)
                intent.putExtra("name", pokemon.name)

                mContext.startActivity(intent)
            }
        }
    }
}
