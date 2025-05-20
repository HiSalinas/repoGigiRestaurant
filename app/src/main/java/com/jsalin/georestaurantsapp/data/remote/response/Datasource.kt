package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Datasource(
    @SerializedName("sourcename")
    val sourceName: String?,
    val attribution: String?,
    val license: String?,
    val url: String?,
    val raw: Raw?
)