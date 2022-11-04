package ru.bykov.footballteams.models

import com.google.gson.annotations.SerializedName

data class FootballTeamDetails(
    val id: Int,
    val name: String,
    val gender: Gender,
    val national: Boolean,
    val description: String,
    @SerializedName("badge_url")
    val badgeUrl: String
)