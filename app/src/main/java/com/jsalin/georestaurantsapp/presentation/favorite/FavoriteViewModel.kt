package com.jsalin.georestaurantsapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsalin.georestaurantsapp.domain.model.RestaurantItem
import com.jsalin.georestaurantsapp.domain.usecase.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteRestaurantState(isLoading = true))
    val state: StateFlow<FavoriteRestaurantState> = _state.asStateFlow()

    init {
        getAllFavoriteRestaurants()
    }

    private fun getAllFavoriteRestaurants() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }

                getAllFavoritesUseCase().collect { restaurantsList ->
                    _state.update {
                        it.copy(
                            restaurants = restaurantsList,
                            isLoading = false,
                            error = if (restaurantsList.isEmpty()) "No hay restaurantes favoritos" else null
                        )
                    }
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

data class FavoriteRestaurantState(
    val restaurants: List<RestaurantItem> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
)