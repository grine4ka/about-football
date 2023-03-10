package ru.bykov.footballteams

import android.app.Application
import ru.bykov.footballteams.di.AppContainer

class FooteaApplication : Application() {

    val appContainer: AppContainer = AppContainer(this)

}