package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.FavoriteRestaurantRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRestaurantRepository
) {
    operator fun invoke(): Flow<List<RestaurantItem>> {
        return repository.getAllFavorites()
    }
}