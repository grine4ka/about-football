package ru.bykov.footballteams.entity

import com.google.gson.annotations.SerializedName

class ApiEnvelope<T>(
    @SerializedName("errors")
    val errors: List<ErrorEntity>,
    @SerializedName("response")
    val response: T,
    @SerializedName("results")
    val results: Int
)