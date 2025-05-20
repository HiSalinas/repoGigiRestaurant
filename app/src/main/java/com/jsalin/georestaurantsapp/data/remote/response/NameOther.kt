package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class NameOther(
    @SerializedName("alt_name")
    val altName: String?
)