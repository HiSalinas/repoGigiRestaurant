package com.jsalin.georestaurantsapp.presentation.restaurantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.usecase.AddFavoriteUseCase
import com.jsalin.georestaurantsapp.domain.usecase.GetLocationUseCase
import com.jsalin.georestaurantsapp.domain.usecase.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RestaurantListState(isLoading = true))
    val state: StateFlow<RestaurantListState> = _state.asStateFlow()

    init {
        getUserLocation()
    }

    fun getUserLocation() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                val location = getLocationUseCase()

                if (location == null) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "No se pudo obtener tu ubicación. "
                        )
                    }
                    return@launch
                }

                getRestaurants(
                    long = location.longitude.toString(),
                    lat = location.latitude.toString()
                )

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar restaurantes: ${e.message}"
                    )
                }
            }
        }
    }

    private fun getRestaurants(long: String, lat: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }

                val restaurantList = getRestaurantsUseCase(long, lat)

                if (!restaurantList.isNullOrEmpty()) {
                    _state.update {
                        it.copy(
                            restaurants = restaurantList,
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    throw Exception()
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar restaurantes: ${e.message}"
                    )
                }
            }
        }
    }
}

data class RestaurantListState(
    val restaurants: List<RestaurantItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)