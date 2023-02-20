package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName

class ApiError(
    @SerializedName("bug")
    val bug: String,
    @SerializedName("report")
    val report: String,
    @SerializedName("time")
    val time: String
)