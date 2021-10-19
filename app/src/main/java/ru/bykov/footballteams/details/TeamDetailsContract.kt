package ru.bykov.footballteams.details

import ru.bykov.footballteams.models.FootballTeamDetails

object TeamDetailsContract {

    interface View {
        fun showDetails(details: FootballTeamDetails)
        fun showError()
    }

    interface Presenter {
        fun loadTeamDetails(teamId: Int)
        fun destroy()
    }
}