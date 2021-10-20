package ru.bykov.footballteams.models

import io.reactivex.Single
import ru.bykov.footballteams.di.BASE_URL
import ru.bykov.footballteams.network.TeamsApi

interface FootballTeamRepository {

    fun teams(): Single<List<FootballTeam>>

    fun details(id: Int): Single<FootballTeamDetails>

    class Impl(private val api: TeamsApi) : FootballTeamRepository {

        override fun teams(): Single<List<FootballTeam>> {
            return api.getTeams()
        }

        override fun details(id: Int): Single<FootballTeamDetails> {
            return api.getTeamDetails(id).map {
                it.copy(badgeUrl = BASE_URL + it.badgeUrl)
            }
        }
    }
}