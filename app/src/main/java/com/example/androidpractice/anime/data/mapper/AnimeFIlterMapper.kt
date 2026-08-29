package com.example.androidpractice.anime.data.mapper

import com.example.androidpractice.anime.domain.model.FilterSetting
import kotlin.enums.enumEntries

inline fun <reified T> String?.toFilterSetting(default: T): T
        where T : Enum<T>, T : FilterSetting {
    return enumEntries<T>().find { it.apiValue == this } ?: default
}