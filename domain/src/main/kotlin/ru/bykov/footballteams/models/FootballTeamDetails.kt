package ru.bykov.footballteams.models

data class FootballTeamDetails(
    val id: Int,
    val name: String,
    val country: String,
    val venue: String,
    val badgeUrl: String
)
