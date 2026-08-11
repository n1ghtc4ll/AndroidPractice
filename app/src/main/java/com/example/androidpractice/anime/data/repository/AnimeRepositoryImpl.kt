package com.example.androidpractice.anime.data.repository

import com.example.androidpractice.anime.data.api.AnimeApi
import com.example.androidpractice.anime.data.mapper.AnimeDtoToEntityMapper
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val mapper: AnimeDtoToEntityMapper
) : AnimeRepository {
    override suspend fun getAnimeById(id: Int): Anime? = withContext(Dispatchers.IO) {
        val response = api.getAnimeById(id)
        val dto = response.data ?: throw Exception()

        mapper.mapItem(dto)
    }

    override suspend fun getAnimeTopList(): List<Anime> = withContext(Dispatchers.IO) {
        val response = api.getTopAnime()
        val dtoList = response.data.orEmpty()

        mapper.mapList(dtoList)
    }
}