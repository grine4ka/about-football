package ru.bykov.footballteams.main

import ru.bykov.footballteams.ui.FootballTeamItem

object MainContract {

    interface View {
        fun showLoading()
        fun showContent(teams: List<FootballTeamItem>)
        fun showError()
    }

    interface Presenter {
        fun loadTeams()
        fun destroy()
    }
}