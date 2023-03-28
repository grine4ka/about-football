package com.bykov.footea.network.model

import com.bykov.footea.database.model.TeamEntity
import com.google.gson.annotations.SerializedName

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
