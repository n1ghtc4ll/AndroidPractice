package com.example.androidpractice.anime.domain.model

import java.time.LocalDate

data class Anime(
    val malId: Int,
    val url: String,
    val imageUrl: String?,
    val title: String,
    val type: String,
    val episodes: Int,
    val status: String,
    val aired: AiredData?,
    val duration: String,
    val rating: String?,
    val score: Double,
    val synopsis: String,
    val genres: List<String>,
)

data class AiredData(
    val from: LocalDate?,
    val to: LocalDate?
)
