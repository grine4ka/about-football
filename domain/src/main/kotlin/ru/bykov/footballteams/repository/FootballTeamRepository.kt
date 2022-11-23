package ru.bykov.footballteams.repository

import io.reactivex.Single
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails

interface FootballTeamRepository {

    fun teams(forceUpdate: Boolean = false): Single<List<FootballTeam>>

    fun details(teamId: Int): Single<FootballTeamDetails>

}
