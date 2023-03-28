package com.bykov.footea

import android.app.Application
import com.bykov.footea.di.AppContainer

class FooteaApplication : Application() {

    val appContainer: AppContainer = AppContainer(this)

}