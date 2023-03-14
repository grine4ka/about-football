package ru.bykov.footballteams.di

import ru.bykov.footballteams.details.TeamDetailsContract
import ru.bykov.footballteams.details.TeamDetailsPresenter
import ru.bykov.footballteams.repository.FootballTeamRepository
import ru.bykov.footballteams.usecase.GetTeamById

class TeamDetailsContainer(
    private val localRepository: FootballTeamRepository,
    private val remoteRepository: FootballTeamRepository,
) {
    fun presenter(teamDetailsView: TeamDetailsContract.View): TeamDetailsPresenter {
        return TeamDetailsPresenter(
            GetTeamById(
                localRepository,
                remoteRepository
            ),
            teamDetailsView
        )
    }
}