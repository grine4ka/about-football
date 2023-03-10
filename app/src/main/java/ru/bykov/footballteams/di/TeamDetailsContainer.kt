package ru.bykov.footballteams.di

import ru.bykov.footballteams.details.TeamDetailsContract
import ru.bykov.footballteams.details.TeamDetailsPresenter
import ru.bykov.footballteams.repository.FootballTeamRepository

class TeamDetailsContainer(
    private val repository: FootballTeamRepository,
) {
    fun presenter(teamDetailsView: TeamDetailsContract.View): TeamDetailsPresenter {
        return TeamDetailsPresenter(repository, teamDetailsView)
    }
}