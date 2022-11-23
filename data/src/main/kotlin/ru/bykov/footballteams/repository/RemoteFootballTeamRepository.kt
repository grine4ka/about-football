package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.entity.toDomain
import ru.bykov.footballteams.extensions.retryExponential
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import ru.bykov.footballteams.network.TeamsApi

class RemoteFootballTeamRepository(
    private val baseUrl: String,
    private val api: TeamsApi,
) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return api.getTeams().retryExponential()
            .map { teams -> teams.map { it.toDomain() } }
    }

    override fun details(teamId: Int): Single<FootballTeamDetails> {
        return api.getTeamDetails(teamId).retryExponential()
            .map { it.toDomain(baseUrl) }
    }
}