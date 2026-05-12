package com.example.androidpractice.core.model

data class Anime(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
    val genres: List<String>,
    val episodes: Int?,
    val score: Double?,
    val description: String
)