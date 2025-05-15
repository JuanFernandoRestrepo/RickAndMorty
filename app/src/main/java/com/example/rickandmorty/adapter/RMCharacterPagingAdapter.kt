package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import RmCharacter
import com.example.rickandmorty.R

class RMCharacterPagingAdapter :
    PagingDataAdapter<RmCharacter, RMCharacterPagingAdapter.RMCharacterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMCharacterViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return RMCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RMCharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class RMCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.name)
        private val idView = itemView.findViewById<TextView>(R.id.id)
        private val type = itemView.findViewById<TextView>(R.id.type)
        private val species = itemView.findViewById<TextView>(R.id.species)
        private val gender = itemView.findViewById<TextView>(R.id.generated)
        private val photo = itemView.findViewById<ImageView>(R.id.imageView2)

        fun bind(character: RmCharacter) {
            name.text = character.name
            idView.text = character.id.toString()
            type.text = character.type
            species.text = character.species
            gender.text = character.gender

            Glide.with(itemView).load(character.image).into(photo)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RmCharacter>() {
            override fun areItemsTheSame(oldItem: RmCharacter, newItem: RmCharacter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RmCharacter, newItem: RmCharacter): Boolean {
                return oldItem == newItem
            }
        }
    }
}