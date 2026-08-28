package com.example.androidpractice.anime.data.mapper

import com.example.androidpractice.anime.data.entity.AnimeEntity
import com.example.androidpractice.anime.data.model.AnimeDto
import com.example.androidpractice.anime.domain.model.AiredData
import com.example.androidpractice.anime.domain.model.Anime
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeParseException
import kotlin.collections.joinToString

fun List<AnimeDto>.toDomainList(): List<Anime> {
    return this.mapNotNull { it.toDomainOrNull() }
}

fun AnimeDto.toDomainOrNull(): Anime? {
    return Anime(
        malId = this.malId ?: return null,
        url = this.url.orEmpty(),
        imageUrl = this.images?.jpg?.imageUrl,
        title = this.titleEnglish ?: this.title ?: "Неизвестно",
        type = this.type ?: "Неизвестно",
        episodes = this.episodes ?: 0,
        status = this.status ?: "Неизвестно",
        aired = AiredData(
            from = parseDateSafe(this.aired?.from),
            to = parseDateSafe(this.aired?.to)
        ),
        duration = this.duration ?: "Неизвестно",
        score = this.score ?: 0.0,
        synopsis = this.synopsis ?: "Описание недоступно.",
        genres = this.genres?.mapNotNull { it.name } ?: emptyList()
    )
}

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
            from = this.airedFrom?.let { LocalDate.parse(it) },
            to = this.airedTo?.let { LocalDate.parse(it) }
        ),
        duration = this.duration,
        score = this.score,
        synopsis = this.synopsis,
        genres = this.genres.split(", ")
    )
}

private fun parseDateSafe(date: String?): LocalDate? {
    if (date.isNullOrEmpty()) return null

    return try {
        LocalDate.parse(date)
    }
    catch (e: DateTimeParseException) {
        null
    }
}
