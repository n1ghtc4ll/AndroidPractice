package com.example.androidpractice.anime.data.repository

import com.example.androidpractice.anime.data.api.AnimeApi
import com.example.androidpractice.anime.data.dao.AnimeDao
import com.example.androidpractice.anime.data.mapper.toDomain
import com.example.androidpractice.anime.data.mapper.toDomainList
import com.example.androidpractice.anime.data.mapper.toDomainOrNull
import com.example.androidpractice.anime.data.mapper.toEntity
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val animeDao: AnimeDao,
) : AnimeRepository {
    override suspend fun getAnimeById(id: Int): Anime? = withContext(Dispatchers.IO) {
        val response = api.getAnimeById(id)
        val dto = response.data ?: throw Exception()

        dto.toDomainOrNull()
    }

    override suspend fun getAnimeTopList(): List<Anime> = withContext(Dispatchers.IO) {
        val response = api.getTopAnime()
        val dtoList = response.data.orEmpty()

        dtoList.toDomainList()
    }

    override fun getFavouriteAnime(): Flow<List<Anime>> {
        return animeDao.getFavouriteAnime().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addFavouriteAnime(anime: Anime) {
        animeDao.insertFavouriteAnime(anime.toEntity())
    }

    override suspend fun deleteFavouriteAnime(id: Int) {
        animeDao.deleteFavouriteAnime(id)
    }

    override suspend fun isAnimeFavourite(id: Int): Boolean {
        return animeDao.isAnimeFavourite(id)
    }
}