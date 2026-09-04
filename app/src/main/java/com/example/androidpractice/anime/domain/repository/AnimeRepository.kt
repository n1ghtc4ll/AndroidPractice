package com.example.androidpractice.anime.domain.repository

import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeById(id: Int): Anime?
    suspend fun getAnimeTopList(
        filter: AnimeFilterSettings
    ): List<Anime>

    fun getFavouriteAnime(
        filter: AnimeFilterSettings
    ): Flow<List<Anime>>
    suspend fun addFavouriteAnime(anime: Anime)
    suspend fun deleteFavouriteAnime(id: Int)
    suspend fun isAnimeFavourite(id: Int): Boolean

    suspend fun saveFilters(settings: AnimeFilterSettings)
    fun getFilters(): Flow<AnimeFilterSettings>
}