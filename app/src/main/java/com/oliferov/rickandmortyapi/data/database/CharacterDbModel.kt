package com.oliferov.rickandmortyapi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterDbModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val avatar: String,
    val location: String,
    val episode: Int
)