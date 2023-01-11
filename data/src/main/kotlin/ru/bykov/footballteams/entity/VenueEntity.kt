package ru.bykov.footballteams.entity

import com.google.gson.annotations.SerializedName

class VenueEntity(
    @SerializedName("address")
    val address: String,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surface")
    val surface: String
)