package com.oliferov.rickandmortyapi.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oliferov.rickandmortyapi.domain.Character

object CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}