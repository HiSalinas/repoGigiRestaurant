package com.jsalin.georestaurantsapp.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object RestaurantList

@Serializable
data class Detail(val idRestaurant: String)

@Serializable
object Favorites