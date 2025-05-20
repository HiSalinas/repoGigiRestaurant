package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.FavoriteRestaurantRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRestaurantRepository
) {
    suspend operator fun invoke(restaurantItem: RestaurantItem) {
        repository.addFavorite(restaurantItem)
    }
}