package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.FavoriteRestaurantRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRestaurantRepository
) {
    suspend operator fun invoke(restaurantId: String) {
        repository.removeFavorite(restaurantId)
    }
}