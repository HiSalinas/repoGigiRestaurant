package com.jsalin.georestaurantsapp.data.remote.response

data class Feature(
    val type: String?,
    val properties: Properties?,
    val geometry: Geometry?
)