package com.example.androidpractice.anime.data.mapper

import com.example.androidpractice.anime.data.entity.AnimeEntity
import com.example.androidpractice.anime.domain.model.AiredData
import com.example.androidpractice.anime.domain.model.Anime
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeParseException
import kotlin.collections.joinToString


fun Anime.toEntity(): AnimeEntity {
    return AnimeEntity(
        malId = this.malId,
        url = this.url,
        imageUrl = this.imageUrl,
        title = this.title,
        type = this.type,
        episodes = this.episodes,
        status = this.status,
        airedFrom = this.aired?.from?.toString(),
        airedTo = this.aired?.to?.toString(),
        duration = this.duration,
        score = this.score,
        synopsis = this.synopsis,
        genres = this.genres.joinToString()
    )
}

fun AnimeEntity.toDomain(): Anime {
    return Anime(
        malId = this.malId,
        url = this.url,
        imageUrl = this.imageUrl,
        title = this.title,
        type = this.type,
        episodes = this.episodes,
        status = this.status,
        aired = AiredData(
            from = this.airedFrom?.let { OffsetDateTime.parse(it).toLocalDate() },
            to = this.airedTo?.let { OffsetDateTime.parse(it).toLocalDate() }
        ),
        duration = this.duration,
        score = this.score,
        synopsis = this.synopsis,
        genres = this.genres.split(", ")
    )
}
