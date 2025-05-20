package com.jsalin.georestaurantsapp.core.local.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteRestaurantEntity::class], version = 1)
abstract class FavoriteRestaurantDataBase : RoomDatabase() {
    abstract fun favoriteRestaurantDao(): FavoriteRestaurantDao
}