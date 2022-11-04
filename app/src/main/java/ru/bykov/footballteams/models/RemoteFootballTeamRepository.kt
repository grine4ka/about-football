package ru.bykov.footballteams.models

import io.reactivex.Single
import ru.bykov.footballteams.di.BASE_URL
import ru.bykov.footballteams.extensions.retryExponential
import ru.bykov.footballteams.network.TeamsApi

class RemoteFootballTeamRepository(private val api: TeamsApi) : FootballTeamRepository {

    override fun teams(forceUpdate: Boolean): Single<List<FootballTeam>> {
        return api.getTeams().retryExponential()
    }

    override fun details(id: Int): Single<FootballTeamDetails> {
        return api.getTeamDetails(id).retryExponential()
            .map {
                it.copy(badgeUrl = BASE_URL + it.badgeUrl)
            }
    }
}