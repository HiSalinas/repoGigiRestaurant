package com.jsalin.georestaurantsapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jsalin.georestaurantsapp.presentation.detail.DetailScreen
import com.jsalin.georestaurantsapp.presentation.favorite.FavoritesScreen
import com.jsalin.georestaurantsapp.presentation.restaurantlist.RestaurantListScreen

@Composable
fun NavigationWrapper(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = RestaurantList) {
        composable<RestaurantList> {
            RestaurantListScreen{ id -> navController.navigate(Detail(idRestaurant = id)) }
        }

        composable<Detail> {
            val detail: Detail = it.toRoute()
            DetailScreen(idRestaurant = detail.idRestaurant,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<Favorites> {
            FavoritesScreen()
        }
    }
}

@Composable
fun NavController.currentRoute(): String? {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}