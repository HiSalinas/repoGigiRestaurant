package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class  Properties(
    val name: String?,
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    val region: String?,
    val state: String?,
    val county: String?,
    val city: String?,
    val postcode: String?,
    val district: String?,
    val street: String?,
    val housenumber: String?,
    @SerializedName("iso3166_2")
    val iso31662: String?,
    @SerializedName("iso3166_2_sublevel")
    val iso31662Sublevel: String?,
    val lon: Double?,
    val lat: Double?,
    val formatted: String?,
    @SerializedName("address_line1")
    val addressLine1: String?,
    @SerializedName("address_line2")
    val addressLine2: String?,
    val categories: List<String>?,
    val details: List<String>?,
    val datasource: Datasource?,
    val brand: String?,
    @SerializedName("brand_details")
    val brandDetails: BrandDetails?,
    @SerializedName("name_international")
    val nameInternational: NameInternational?,
    @SerializedName("name_other")
    val nameOther: NameOther?,
    val catering: Catering?,
    val contact: Contact?,
    val facilities: Facilities?,
    @SerializedName("opening_hours")
    val openingHours: String?,
    val distance: Int?,
    @SerializedName("place_id")
    val placeId: String?,
    @SerializedName("feature_type")
    val featureType: String?,
    val timezone: Timezone?
)