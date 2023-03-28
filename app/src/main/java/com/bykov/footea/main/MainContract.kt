package com.bykov.footea.main

import com.bykov.footea.ui.FootballTeamItem

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