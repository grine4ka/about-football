package ru.bykov.footballteams.network.model

import com.google.gson.annotations.SerializedName
import ru.bykov.footballteams.database.model.TeamEntity

class ApiFullTeam(
    @SerializedName("team")
    val team: ApiTeam,
    @SerializedName("venue")
    val venue: ApiVenue
)

fun ApiFullTeam.toTeamEntity(): TeamEntity = TeamEntity(
    team.id,
    team.name,
    team.logo,
    team.country,
    venue.name,
    venue.capacity.toString()
)
