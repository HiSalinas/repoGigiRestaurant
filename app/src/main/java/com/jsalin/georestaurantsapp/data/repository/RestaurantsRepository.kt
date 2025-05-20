package com.jsalin.georestaurantsapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.jsalin.georestaurantsapp.data.remote.ApiServiceRestaurants
import com.jsalin.georestaurantsapp.domain.interfaces.IRestaurantsRepository
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.model.UserLocation
import com.jsalin.georestaurantsapp.domain.model.toRestaurantItems
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RestaurantsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ApiServiceRestaurants
): IRestaurantsRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation(): UserLocation? {
        val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.getProviders(true)
        if (providers.isEmpty()) {
            Log.d("NativeLocationClient", "No hay proveedores de ubicación disponibles")
            return null
        }
        var bestLocation: Location? = null

        for (provider in providers) {
            try {
                val location = locationManager.getLastKnownLocation(provider)
                if (location != null && (bestLocation == null ||
                            location.accuracy < bestLocation.accuracy)) {
                    bestLocation = location
                }
            } catch (e: Exception) {
                Log.e("NativeLocationClient", "Error al obtener ubicación de $provider: ${e.message}")
            }
        }
        return bestLocation?.let { UserLocation(it.latitude, it.longitude) }
    }

    override suspend fun getRestaurants(long: String, lat: String): List<RestaurantItem>? {
        val filter = "circle:$long,$lat,5000"
        val bias = "proximity:$long,$lat"

        return withContext(Dispatchers.IO) {
            val restaurant = api.getRestaurants(filter = filter, bias = bias)
            restaurant.toRestaurantItems()
        }
    }

    override suspend fun getRestaurantByID(idRestaurant:String): RestaurantItem? {
        return withContext(Dispatchers.IO) {
            val restaurant = api.getRestaurantID(
                idRestaurant = idRestaurant
            )
            restaurant.toRestaurantItems().firstOrNull()
        }
    }
}