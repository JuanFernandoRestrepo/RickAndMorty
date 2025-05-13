package com.example.rickandmorty

data class Characters(
    val page: Int?,
    val result: List<Character>
)
data class Character(
    val name: String?,
    val photo: String?,
    val year: Int?,
    val species: String?,
    val type: String?,
    val generated: String?
)