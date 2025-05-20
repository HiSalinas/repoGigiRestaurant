package com.jsalin.georestaurantsapp.domain.usecase

import com.jsalin.georestaurantsapp.data.repository.RestaurantsRepository
import com.jsalin.georestaurantsapp.domain.model.UserLocation
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) {
    suspend operator fun invoke(): UserLocation? {
        return restaurantsRepository.getUserLocation()
    }
}