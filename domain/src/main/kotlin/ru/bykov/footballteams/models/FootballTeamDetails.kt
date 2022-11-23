package ru.bykov.footballteams.models

class FootballTeamDetails(
    val id: Int,
    val name: String,
    val gender: String,
    val national: Boolean,
    val description: String,
    val badgeUrl: String
)