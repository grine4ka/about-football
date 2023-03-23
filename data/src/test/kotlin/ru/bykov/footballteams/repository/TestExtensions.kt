package ru.bykov.footballteams.repository

import ru.bykov.footballteams.database.model.TeamEntity
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.network.model.ApiFullTeam
import ru.bykov.footballteams.network.model.ApiTeam
import ru.bykov.footballteams.network.model.ApiVenue

internal fun FootballTeam.toTeamEntity(): TeamEntity {
    return TeamEntity(
        id = id,
        name = name,
        logo = logoUrl,
        country = "",
        venueName = "",
        venueCapacity = "",
    )
}

internal fun FootballTeam.toTeamApi(): ApiFullTeam {
    return ApiFullTeam(
        ApiTeam(
            id = id,
            name = name,
            logo = logoUrl,
            country = "",
            code = "",
            founded = 0,
            national = false
        ),
        ApiVenue(
            name = "",
            capacity = 0,
            address = "",
            city = "",
            id = 0,
            image = "",
            surface = "",
        )
    )
}
