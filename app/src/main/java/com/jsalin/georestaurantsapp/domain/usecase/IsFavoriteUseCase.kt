package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.FavoriteRestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRestaurantRepository
) {
    operator fun invoke(restaurantId: String): Flow<Boolean> {
        return repository.isFavorite(restaurantId)
    }
}