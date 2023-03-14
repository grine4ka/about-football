package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.database.TeamsDao
import ru.bykov.footballteams.database.model.toTeam
import ru.bykov.footballteams.database.model.toTeamDetails
import ru.bykov.footballteams.extensions.retryExponential
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi
import ru.bykov.footballteams.network.model.toTeamEntity

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