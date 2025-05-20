package com.jsalin.georestaurantsapp.domain.interfaces

import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.model.UserLocation

interface IRestaurantsRepository {
    suspend fun getUserLocation(): UserLocation?
    suspend fun getRestaurants(long: String, lat: String): List<RestaurantItem>?
    suspend fun getRestaurantByID(idRestaurant:String): RestaurantItem?
}