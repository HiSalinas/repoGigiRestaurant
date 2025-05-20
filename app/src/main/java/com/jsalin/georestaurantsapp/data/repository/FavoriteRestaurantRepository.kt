package com.jsalin.georestaurantsapp.data.repository

import com.jsalin.georestaurantsapp.core.local.database.FavoriteRestaurantDao
import com.jsalin.georestaurantsapp.core.local.database.FavoriteRestaurantEntity
import com.jsalin.georestaurantsapp.core.local.database.toFavoriteRestaurant
import com.jsalin.georestaurantsapp.domain.interfaces.IFavoriteRestaurantRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRestaurantRepository @Inject constructor(
    private val favoriteRestaurantDao: FavoriteRestaurantDao
) : IFavoriteRestaurantRepository {
    override suspend fun addFavorite(restaurantItem: RestaurantItem) {
        favoriteRestaurantDao.addFavorite(restaurantItem.toFavoriteRestaurant())
    }

    override suspend fun removeFavorite(restaurantId: String) {
       favoriteRestaurantDao.removeFavoriteById(restaurantId)
    }

    override fun getAllFavorites(): Flow<List<RestaurantItem>> {
        return favoriteRestaurantDao.getAllFavorites().map { favorites ->
            favorites.map { it.toRestaurantItem() }
        }
    }

    override fun isFavorite(restaurantId: String): Flow<Boolean> {
        return favoriteRestaurantDao.isFavorite(restaurantId)
    }

}

fun FavoriteRestaurantEntity.toRestaurantItem(): RestaurantItem {
    return RestaurantItem(
        restaurantId = restaurantId,
        title = title,
        category = category,
        street = street,
        distance = distance
    )
}