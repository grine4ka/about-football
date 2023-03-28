package com.bykov.footea.repository

import io.reactivex.Completable
import io.reactivex.Single

interface StringPreferencesRepository {

    fun getPref(key: String, defaultValue: String): Single<String>
    fun setPref(key: String, value: String): Completable

}