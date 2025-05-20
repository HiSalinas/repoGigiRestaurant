package com.jsalin.georestaurantsapp.domain.model

import com.jsalin.georestaurantsapp.data.remote.response.Feature
import com.jsalin.georestaurantsapp.data.remote.response.Restaurant

data class RestaurantItem(
    val restaurantId: String,
    val title: String,
    val category: String,
    val street: String,
    val distance: String
)

fun Restaurant.toRestaurantItems(): List<RestaurantItem> {
    return features?.mapNotNull { feature ->
        feature.toRestaurantItem()
    } ?: emptyList()
}

fun Feature.toRestaurantItem(): RestaurantItem? {
    val properties = this.properties ?: return null

    val restaurantId = properties.placeId ?: return null

    val title = properties.name ?: "Restaurante sin nombre"

    val category = properties.catering?.cuisine ?: "Restaurante"

    val street = properties.street ?: properties.addressLine2 ?: ""

    val distance = properties.distance

    return RestaurantItem(
        restaurantId = restaurantId,
        title = title,
        category = category,
        street = street,
        distance = distance.toString()
    )
}