package com.oliferov.rickandmortyapi.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.oliferov.rickandmortyapi.databinding.ItemCharacterBinding
import com.oliferov.rickandmortyapi.domain.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(private val context: Context) :
    ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback) {

    var onCharacterClickListener: OnCharacterClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        with(holder.binding) {
            with(character) {
                Picasso.get().load(avatarUrl).into(ivAvatar)
                tvName.text = name
                tvGender.text = gender
                tvSpecies.text = species
                root.setOnClickListener {
                    onCharacterClickListener?.onCharacterClick(this)
                }
            }
        }
    }

    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }
}