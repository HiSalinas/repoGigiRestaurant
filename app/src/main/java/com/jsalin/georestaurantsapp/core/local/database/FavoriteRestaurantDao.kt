package com.jsalin.georestaurantsapp.core.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRestaurantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(restaurant: FavoriteRestaurantEntity)

    @Delete
    suspend fun removeFavorite(restaurant: FavoriteRestaurantEntity)

    @Query("DELETE FROM favorite_restaurants WHERE restaurantId = :restaurantId")
    suspend fun removeFavoriteById(restaurantId: String)

    @Query("SELECT * FROM favorite_restaurants")
    fun getAllFavorites(): Flow<List<FavoriteRestaurantEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_restaurants WHERE restaurantId = :id)")
    fun isFavorite(id: String): Flow<Boolean>
}