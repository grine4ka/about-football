package ru.bykov.footballteams.di

import ru.bykov.footballteams.details.TeamDetailsActivity
import ru.bykov.footballteams.details.TeamDetailsContract
import ru.bykov.footballteams.details.TeamDetailsPresenter

class TeamDetailsInjection(
    activity: TeamDetailsActivity
) {
    val presenter: TeamDetailsContract.Presenter by lazy(LazyThreadSafetyMode.NONE) {
        TeamDetailsPresenter(Injection.repository, activity)
    }
}