package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Facilities(
    @SerializedName("internet_access")
    val internetAccess: Boolean?
)