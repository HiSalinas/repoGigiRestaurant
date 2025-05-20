package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.RestaurantsRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) {
    suspend operator fun invoke(long: String, lat: String): List<RestaurantItem>? {
        return restaurantsRepository.getRestaurants(long, lat)
    }
}