package com.example.androidpractice.anime_list.domain.repository

import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeById(id: Int): AnimeEntity?
    suspend fun getAnimeTopList(
        type: AnimeType? = null,
        rating: AnimeRating? = null,
        filter: AnimeFilter? = null,
    ): List<AnimeEntity>
    fun getFavouriteAnimeList(): Flow<List<AnimeEntity>>
    suspend fun saveFavouriteAnime(anime: AnimeEntity)
    suspend fun deleteFavouriteAnime(id: Int)
    fun isFavourite(id: Int): Flow<Boolean>
    fun getFilterSettings(): Flow<AnimeFilterSettings>
    suspend fun saveFilterSettings(settings: AnimeFilterSettings)
}