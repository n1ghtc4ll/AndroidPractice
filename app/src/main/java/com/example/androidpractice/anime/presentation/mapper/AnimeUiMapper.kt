package com.example.androidpractice.anime.presentation.mapper

import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.presentation.model.AnimeUiModel

fun Anime.toUiModel(): AnimeUiModel = AnimeUiModel(
    id = this.malId,
    title = this.title,
    url = this.url,
    imageUrl = this.imageUrl,
    type = this.type,
    genres = if (this.genres.isEmpty()) "Нет жанров" else this.genres.joinToString(),
    episodes = this.episodes.toString(),
    score = this.score.toString(),
    description = this.synopsis,
    domainModel = this
)

fun List<Anime>.toUiModels(): List<AnimeUiModel> = this.map { it.toUiModel() }