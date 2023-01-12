package ru.bykov.footballteams.entity

import com.google.gson.annotations.SerializedName
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

class FullTeamEntity(
    @SerializedName("team")
    val team: TeamEntity,
    @SerializedName("venue")
    val venue: VenueEntity
)

fun FullTeamEntity.toTeam(): FootballTeam = FootballTeam(
    team.id,
    team.name
)

fun FullTeamEntity.toTeamDetails(): FootballTeamDetails = FootballTeamDetails(
    team.id,
    team.name,
    team.national,
    "${venue.name} with capacity ${venue.capacity}", // TODO move to a separate class
    team.logo
)
