package com.jsalin.georestaurantsapp.data.remote

import com.jsalin.georestaurantsapp.BuildConfig
import com.jsalin.georestaurantsapp.data.remote.response.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceRestaurants {
    @GET("places")
    suspend fun getRestaurants(
        @Query("categories") categories: String = "catering.restaurant",
        @Query("filter") filter: String,
        @Query("bias") bias: String,
        @Query("limit") limit: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY_RESTAURANTS
    ): Restaurant

    @GET("place-details")
    suspend fun getRestaurantID(
        @Query("id") idRestaurant: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY_RESTAURANTS
    ): Restaurant
}