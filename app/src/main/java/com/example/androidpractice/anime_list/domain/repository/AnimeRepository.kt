package com.example.androidpractice.anime_list.domain.repository

import com.example.androidpractice.anime_list.domain.model.AnimeEntity

interface AnimeRepository {
    suspend fun getAnimeById(id: Int): AnimeEntity?
    suspend fun getAnimeTopList(): List<AnimeEntity>
}