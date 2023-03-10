package ru.bykov.footballteams.di

import android.content.Context
import ru.bykov.footballteams.BuildConfig
import ru.bykov.footballteams.Secrets
import ru.bykov.footballteams.repository.FootballTeamRepository

class AppContainer(context: Context) {

    private val secrets: Secrets = Secrets()
    private val dataContainer: DataContainer = DataContainer(context)

    val repository: FootballTeamRepository by lazy(LazyThreadSafetyMode.NONE) {
        dataContainer.repository(secrets.getApiFootballKey(BuildConfig.APPLICATION_ID))
    }

    var teamDetailsContainer: TeamDetailsContainer? = null
    var teamListContainer: TeamListContainer? = null

}