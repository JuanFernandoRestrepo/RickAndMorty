package com.example.rickandmorty.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import RmCharacter

class CharacterViewHolder (view: View): RecyclerView.ViewHolder(view){
    val name =view.findViewById<TextView>(R.id.name)
    val id =view.findViewById<TextView>(R.id.id)
    val type =view.findViewById<TextView>(R.id.type)
    val species =view.findViewById<TextView>(R.id.species)
    val gender =view.findViewById<TextView>(R.id.generated)
    val photo =view.findViewById<ImageView>(R.id.imageView2)

    fun render(rmCharacter: RmCharacter) {
        name.text = rmCharacter.name
        id.text = rmCharacter.id.toString()
        type.text = rmCharacter.type
        species.text = rmCharacter.species
        gender.text = rmCharacter.gender

        Glide.with(photo.context).load(rmCharacter.image).into(photo)
    }

}