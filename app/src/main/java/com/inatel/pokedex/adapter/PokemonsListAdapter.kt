package com.inatel.pokedex.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.inatel.pokedex.R
import com.inatel.pokedex.model.Pokemons
import com.inatel.pokedex.views.PokemonActivity
import kotlinx.android.synthetic.main.poke_item.view.*
import org.jetbrains.anko.startActivity

/**
 *
 * @author lucasmarciano
 * @version 1.0.0
 */
class PokemonsListAdapter(
        val pokemons: List<Pokemons>,
        val mContext: Context) : Adapter<PokemonsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.poke_item, parent, false))
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
        fun bindView(pokemon: Pokemons) {
            val name = itemView.tvNamePokemon
            val tipo = itemView.tvTipo
            var aux = ""

            for(value in pokemon.types)
                aux = "$aux$value "


            name.text = pokemon.name
            tipo.text = aux
            Glide.with(mContext).load(pokemon.picture).into(itemView.ivPokemon)

            itemView.setOnClickListener {
                mContext.startActivity<PokemonActivity>("id" to pokemon._id)
            }
        }
    }
}
