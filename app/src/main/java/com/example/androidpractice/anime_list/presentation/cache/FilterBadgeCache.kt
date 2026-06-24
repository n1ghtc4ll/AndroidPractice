package com.example.androidpractice.anime_list.presentation.cache

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilterBadgeCache {
    private val _isBadgeVisible = MutableStateFlow(false)
    val isBadgeVisible = _isBadgeVisible.asStateFlow()

    fun updateBadgeState(hasActiveFilters: Boolean) {
        _isBadgeVisible.value = hasActiveFilters
    }
}