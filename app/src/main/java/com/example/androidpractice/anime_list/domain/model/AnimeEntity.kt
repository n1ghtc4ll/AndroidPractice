package com.example.androidpractice.anime_list.domain.model

import com.example.androidpractice.anime_list.data.model.AnimeImagesDto
import java.time.LocalDate

data class AnimeEntity(
    val malId: Int,
    val url: String,
    val imageUrl: String?,
    val title: String,
    val type: String,
    val episodes: Int,
    val status: String,
    val aired: AiredData?,
    val duration: String,
    val score: Double,
    val synopsis: String,
    val genres: List<String>,
)

data class AiredData(
    val from: LocalDate?,
    val to: LocalDate?
)
