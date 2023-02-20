package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName

class ApiTeam(
    @SerializedName("code")
    val code: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("national")
    val national: Boolean
)