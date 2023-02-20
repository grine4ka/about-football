package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName

class ApiEnvelope<T>(
    @SerializedName("errors")
    val errors: List<ApiError>,
    @SerializedName("response")
    val response: T,
    @SerializedName("results")
    val results: Int
)