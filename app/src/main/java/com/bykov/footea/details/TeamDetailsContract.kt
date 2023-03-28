package com.bykov.footea.details

import com.bykov.footea.models.FootballTeamDetails

object TeamDetailsContract {

    interface View {
        fun showDetails(details: FootballTeamDetails)
        fun showError(message: String?)
    }

    interface Presenter {
        fun loadTeamDetails(teamId: Int)
        fun destroy()
    }
}