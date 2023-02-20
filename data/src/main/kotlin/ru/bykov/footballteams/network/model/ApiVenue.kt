package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName

class ApiVenue(
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