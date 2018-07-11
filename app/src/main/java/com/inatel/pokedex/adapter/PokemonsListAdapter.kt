package com.inatel.pokedex.adapter

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

        fun bindView(pokemon: Pokemons) {
            val name = itemView.tvNamePokemon
            val descricao = itemView.tvDescricaoPokemon

            val tipo = itemView.tvTipo
            val forca = itemView.tvForca
            val velocidade = itemView.tvVelocidade

            name.text = pokemon.title
            descricao.text = pokemon.body
            Glide.with(mContext).load(pokemon.image).into(itemView.ivPokemon)

            itemView.setOnClickListener {
                mContext.startActivity<PokemonActivity>("id" to pokemon.id)
            }
        }
    }
}
