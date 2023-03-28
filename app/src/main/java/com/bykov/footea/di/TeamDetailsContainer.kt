package com.bykov.footea.di

import com.bykov.footea.details.TeamDetailsContract
import com.bykov.footea.details.TeamDetailsPresenter
import com.bykov.footea.repository.FootballTeamRepository
import com.bykov.footea.usecase.GetTeamById

class TeamDetailsContainer(
    private val repository: FootballTeamRepository,
) {
    fun presenter(teamDetailsView: TeamDetailsContract.View): TeamDetailsPresenter {
        return TeamDetailsPresenter(
            GetTeamById(repository),
            teamDetailsView
        )
    }
}