package com.bykov.footea.di

import android.content.Context
import com.bykov.footea.BuildConfig
import com.bykov.footea.Secrets
import com.bykov.footea.repository.FootballTeamRepository
import com.bykov.footea.repository.StringPreferencesRepository

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