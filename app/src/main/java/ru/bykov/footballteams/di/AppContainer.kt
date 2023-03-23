package ru.bykov.footballteams.di

import android.content.Context
import ru.bykov.footballteams.BuildConfig
import ru.bykov.footballteams.Secrets
import ru.bykov.footballteams.repository.FootballTeamRepository
import ru.bykov.footballteams.repository.StringPreferencesRepository

class AppContainer(context: Context) {

    private val secrets: Secrets = Secrets()
    private val dataContainer: DataContainer = DataContainer(context)

    val preferencesRepository: StringPreferencesRepository by lazy(LazyThreadSafetyMode.NONE) {
        dataContainer.stringPreferencesRepository()
    }

    val remoteRepository: FootballTeamRepository by lazy(LazyThreadSafetyMode.NONE) {
        dataContainer.remoteRepository(secrets.getApiFootballKey(BuildConfig.APPLICATION_ID))
    }

    val localRepository: FootballTeamRepository by lazy(LazyThreadSafetyMode.NONE) {
        dataContainer.localRepository(remoteRepository)
    }

    var teamDetailsContainer: TeamDetailsContainer? = null
    var teamListContainer: TeamListContainer? = null

}