package com.jsalin.georestaurantsapp.core.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem

@Entity(tableName = "favorite_restaurants")
data class FavoriteRestaurantEntity(
    @PrimaryKey val restaurantId: String,
    val title: String,
    val category: String,
    val street: String,
    val distance: String
)

fun RestaurantItem.toFavoriteRestaurant(): FavoriteRestaurantEntity {
    return FavoriteRestaurantEntity(
        restaurantId = restaurantId,
        title = title,
        category = category,
        street = street,
        distance = distance
    )
}