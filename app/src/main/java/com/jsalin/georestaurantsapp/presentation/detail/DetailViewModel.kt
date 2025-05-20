package com.jsalin.georestaurantsapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.usecase.AddFavoriteUseCase
import com.jsalin.georestaurantsapp.domain.usecase.GetLocationUseCase
import com.jsalin.georestaurantsapp.domain.usecase.GetRestaurantUseCase
import com.jsalin.georestaurantsapp.domain.usecase.GetRestaurantsUseCase
import com.jsalin.georestaurantsapp.domain.usecase.IsFavoriteUseCase
import com.jsalin.georestaurantsapp.domain.usecase.RemoveFavoriteUseCase
import com.jsalin.georestaurantsapp.presentation.restaurantlist.RestaurantListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase

): ViewModel() {
    private val _state = MutableStateFlow(RestaurantDetailState(isLoading = true))
    val state: StateFlow<RestaurantDetailState> = _state.asStateFlow()

    fun getRestaurantById(idRestaurant: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                val restaurant = getRestaurantUseCase(idRestaurant = idRestaurant)

                if (restaurant == null) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "No se pudo recuperar el restaurante. "
                        )
                    }
                    return@launch
                }

                _state.update {
                    it.copy(
                        restaurant = restaurant,
                        isLoading = false,
                        error = null
                    )
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al cargar restaurante: ${e.message}"
                    )
                }
            }
        }
    }

    private fun removeFromFavorites(restaurantId: String) {
        viewModelScope.launch {
            try {
                removeFavoriteUseCase(restaurantId)

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al eliminar restaurante de favoritos: ${e.message}"
                    )
                }
            }
        }
    }

    private fun addFavorites(restaurant: RestaurantItem) {
        viewModelScope.launch {
            try {
                addFavoriteUseCase(restaurant)

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al añadir restaurante a favoritos: ${e.message}"
                    )
                }
            }
        }
    }

    fun isFavoriteRestaurant(idRestaurant: String) {
        viewModelScope.launch {
            try {
                isFavoriteUseCase(idRestaurant).collect { isFavorite ->
                    _state.update { it.copy(isFavorite = isFavorite) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Error al verificar favorito: ${e.message}")
                }
            }
        }
    }

    fun toggleFavorite() {
        val restaurant = state.value.restaurant ?: return

        viewModelScope.launch {
            if (state.value.isFavorite) {
                removeFromFavorites(restaurant.restaurantId)
            } else {

                addFavorites(restaurant)
            }
        }
    }
}

data class RestaurantDetailState(
    val restaurant: RestaurantItem? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false
)