package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.extensions.retryExponential
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi
import ru.bykov.footballteams.network.model.toTeam
import ru.bykov.footballteams.network.model.toTeamDetails

class RemoteFootballTeamRepository(
    private val api: TeamsApi,
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return api.getTeams().retryExponential()
            .map { envelope -> envelope.response.map { it.toTeam() } }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return api.getTeamDetails(teamId).retryExponential()
            .map { envelope -> envelope.response.first().toTeamDetails() }
    }
}