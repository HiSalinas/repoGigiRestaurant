package com.jsalin.georestaurantsapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.jsalin.georestaurantsapp.R
import com.jsalin.georestaurantsapp.core.navigation.Directions
import com.jsalin.georestaurantsapp.core.navigation.Favorites
import com.jsalin.georestaurantsapp.core.navigation.NavigationWrapper
import com.jsalin.georestaurantsapp.core.navigation.RestaurantList
import com.jsalin.georestaurantsapp.core.navigation.currentRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun RestaurantNavigationBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val currentRoute = navController.currentRoute()

    val items = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.home_item_title),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            RestaurantList
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.favorites_item_title),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            Favorites
        ),
    )

    val shouldShowBottomBar = currentRoute?.contains(Directions.Detail.route) != true

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (shouldShowBottomBar) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                if (currentRoute != item.screen) {
                                    navController.navigate(item.screen)
                                    {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationWrapper(navController = navController)
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val screen: Any
)