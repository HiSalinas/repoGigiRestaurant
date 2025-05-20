package com.jsalin.georestaurantsapp.domain.interfaces

import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import kotlinx.coroutines.flow.Flow

interface IFavoriteRestaurantRepository {
    suspend fun addFavorite(restaurantItem: RestaurantItem)
    suspend fun removeFavorite(restaurantId: String)
    fun getAllFavorites(): Flow<List<RestaurantItem>>
    fun isFavorite(restaurantId: String): Flow<Boolean>
}