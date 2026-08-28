package com.example.androidpractice.anime.domain.repository

import com.example.androidpractice.anime.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeById(id: Int): Anime?
    suspend fun getAnimeTopList(): List<Anime>

    fun getFavouriteAnime(): Flow<List<Anime>>
    suspend fun addFavouriteAnime(anime: Anime)
    suspend fun deleteFavouriteAnime(id: Int)
    suspend fun isAnimeFavourite(id: Int): Boolean
}