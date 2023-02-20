package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

class ApiFullTeam(
    @SerializedName("team")
    val team: ApiTeam,
    @SerializedName("venue")
    val venue: ApiVenue
)

fun ApiFullTeam.toTeam(): FootballTeam = FootballTeam(
    team.id,
    team.name,
    team.logo
)

fun ApiFullTeam.toTeamDetails(): FootballTeamDetails = FootballTeamDetails(
    team.id,
    team.name,
    team.country,
    "${venue.name} with capacity ${venue.capacity}", // TODO move to a separate class
    team.logo
)
