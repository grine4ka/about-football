package com.bykov.footea.repository

import com.bykov.footea.database.TeamsDao
import com.bykov.footea.database.model.toTeam
import com.bykov.footea.database.model.toTeamDetails
import com.bykov.footea.extensions.retryExponential
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.models.FootballTeamDetails
import com.bykov.footea.network.TeamsApi
import com.bykov.footea.network.model.toTeamEntity
import io.reactivex.Single

class RemoteFootballTeamRepository(
    private val api: TeamsApi,
    private val dao: TeamsDao
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return api.getTeams().retryExponential()
            .map { envelope ->
                val fullTeams = envelope.response.map { it.toTeamEntity() }
                dao.insertOrIgnoreTeams(fullTeams)
                fullTeams.map { it.toTeam() }
            }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return api.getTeamDetails(teamId).retryExponential()
            .map { envelope ->
                val team = envelope.response.first().toTeamEntity()
                dao.insertOrIgnoreTeams(listOf(team))
                team.toTeamDetails()
            }
    }
}