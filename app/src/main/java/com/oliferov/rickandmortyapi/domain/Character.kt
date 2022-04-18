package com.oliferov.rickandmortyapi.domain

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val status: String,
    val avatarUrl: String,
    val location: String,
    val episode: Int
)