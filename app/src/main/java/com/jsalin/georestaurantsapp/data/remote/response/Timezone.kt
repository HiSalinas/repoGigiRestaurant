package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class Timezone(
    val name: String?,
    @SerializedName("offset_STD")
    val offsetStd: String?,
    @SerializedName("offset_STD_seconds")
    val offsetStdSeconds: Int?,
    @SerializedName("offset_DST")
    val offsetDst: String?,
    @SerializedName("offset_DST_seconds")
    val offsetDstSeconds: Int?,
    @SerializedName("abbreviation_STD")
    val abbreviationStd: String?,
    @SerializedName("abbreviation_DST")
    val abbreviationDst: String?
)