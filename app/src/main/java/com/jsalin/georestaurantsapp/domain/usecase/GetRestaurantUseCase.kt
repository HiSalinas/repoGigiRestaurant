package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.remote.response.Restaurant
import com.jsalin.georestaurantsapp.data.repository.RestaurantsRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.model.UserLocation
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) {
    suspend operator fun invoke(idRestaurant: String): RestaurantItem? {
        return restaurantsRepository.getRestaurantByID(idRestaurant)
    }
}