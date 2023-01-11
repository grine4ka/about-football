package ru.bykov.footballteams.entity

import com.google.gson.annotations.SerializedName

class ErrorEntity(
    @SerializedName("bug")
    val bug: String,
    @SerializedName("report")
    val report: String,
    @SerializedName("time")
    val time: String
)