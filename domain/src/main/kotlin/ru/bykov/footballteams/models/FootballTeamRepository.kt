package ru.bykov.footballteams.models

import io.reactivex.Single

interface FootballTeamRepository {

    fun teams(forceUpdate: Boolean = false): Single<List<FootballTeam>>

    fun details(id: Int): Single<FootballTeamDetails>

}
