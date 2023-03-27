package com.bykov.footea.repository

import com.bykov.footea.database.model.TeamEntity
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.network.model.ApiFullTeam
import com.bykov.footea.network.model.ApiTeam
import com.bykov.footea.network.model.ApiVenue

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
