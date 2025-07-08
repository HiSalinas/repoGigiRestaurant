package com.jsalin.georestaurantsapp.core.navigation

import com.jsalin.georestaurantsapp.utils.DETAIL_SCREEN
import com.jsalin.georestaurantsapp.utils.FAVORITE_SCREEN
import com.jsalin.georestaurantsapp.utils.HOME_SCREEN
import com.jsalin.georestaurantsapp.utils.RESTAURANT_LIST_SCREEN

sealed class Directions(val route: String) {
    object Home: Directions(HOME_SCREEN)
    object Detail: Directions(DETAIL_SCREEN)
    object RestaurantList: Directions(RESTAURANT_LIST_SCREEN)
    object Favorite: Directions(FAVORITE_SCREEN)
}