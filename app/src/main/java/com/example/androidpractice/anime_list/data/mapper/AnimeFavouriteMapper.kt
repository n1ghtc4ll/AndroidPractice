package com.example.androidpractice.anime_list.data.mapper

import com.example.androidpractice.anime_list.data.entity.AnimeDbEntity
import com.example.androidpractice.anime_list.domain.model.AiredData
import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import java.time.LocalDate

class AnimeFavouriteMapper {

    fun mapToDomain(dbEntity: AnimeDbEntity): AnimeEntity {
        return AnimeEntity(
            malId = dbEntity.id,
            url = dbEntity.url,
            imageUrl = dbEntity.imageUrl,
            title = dbEntity.title,
            type = dbEntity.type,
            episodes = dbEntity.episodes,
            status = dbEntity.status,
            aired = AiredData(
                from = dbEntity.airedFrom?.let { LocalDate.parse(it) },
                to = dbEntity.airedTo?.let { LocalDate.parse(it) }
            ),
            duration = dbEntity.duration,
            score = dbEntity.score,
            synopsis = dbEntity.synopsis,
            genres = if (dbEntity.genres.isEmpty()) emptyList() else dbEntity.genres.split(", ")
        )
    }

    fun mapToDb(entity: AnimeEntity): AnimeDbEntity {
        return AnimeDbEntity(
            id = entity.malId,
            url = entity.url,
            imageUrl = entity.imageUrl,
            title = entity.title,
            type = entity.type,
            episodes = entity.episodes,
            status = entity.status,
            airedFrom = entity.aired?.from?.toString(), // LocalDate легко переводится в "YYYY-MM-DD"
            airedTo = entity.aired?.to?.toString(),
            duration = entity.duration,
            score = entity.score,
            synopsis = entity.synopsis,
            genres = entity.genres.joinToString(", ")
        )
    }
}