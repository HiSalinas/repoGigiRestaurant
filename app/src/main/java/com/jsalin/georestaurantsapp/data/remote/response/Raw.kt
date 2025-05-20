package com.jsalin.georestaurantsapp.data.remote.response

import com.google.gson.annotations.SerializedName


data class Raw(
    @SerializedName("addr:city")
    val addrCity: String?,
    @SerializedName("addr:housenumber")
    val addrHouseNumber: Int?,
    @SerializedName("addr:postcode")
    val addrPostcode: String?,
    @SerializedName("addr:street")
    val addrStreet: String?,
    @SerializedName("alt_name")
    val altName: String?,
    val amenity: String?,
    val brand: String?,
    @SerializedName("brand:wikidata")
    val brandWikidata: String?,
    @SerializedName("brand:wikipedia")
    val brandWikipedia: String?,
    val building: String?,
    @SerializedName("contact:facebook")
    val contactFacebook: String?,
    val cuisine: String?,
    @SerializedName("internet_access")
    val internetAccess: String?,
    @SerializedName("internet_access:fee")
    val internetAccessFee: String?,
    val name: String?,
    @SerializedName("name:ca")
    val nameCa: String?,
    @SerializedName("opening_hours")
    val openingHours: String?,
    @SerializedName("osm_id")
    val osmId: Long?,
    @SerializedName("osm_type")
    val osmType: String?,
    val phone: String?,
    val website: String?
)