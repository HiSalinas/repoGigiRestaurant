package com.jsalin.georestaurantsapp.di

import android.content.Context
import androidx.room.Room
import com.jsalin.georestaurantsapp.core.local.database.FavoriteRestaurantDao
import com.jsalin.georestaurantsapp.core.local.database.FavoriteRestaurantDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FavoriteRestaurantDataBase {
        return Room.databaseBuilder(
            context,
            FavoriteRestaurantDataBase::class.java,
            "restaurant_database"
        ).build()
    }

    @Provides
    fun provideFavoriteRestaurantDao(database: FavoriteRestaurantDataBase): FavoriteRestaurantDao {
        return database.favoriteRestaurantDao()
    }
}