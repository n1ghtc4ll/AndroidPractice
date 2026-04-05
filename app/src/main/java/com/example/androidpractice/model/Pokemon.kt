package com.example.androidpractice.model

data class Pokemon(
    val name: String,
    val type: Pair<String, String?>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val imageUrl: String?
)