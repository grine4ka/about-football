package ru.bykov.footballteams.repository

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single

class StringSharedPreferencesRepository(
    private val sharedPreferences: SharedPreferences
) : StringPreferencesRepository {

    override fun getPref(key: String, defaultValue: String): Single<String> {
        return Single.fromCallable {
            sharedPreferences.getString(key, defaultValue)
        }
    }

    override fun setPref(key: String, value: String): Completable {
        return Single
            .fromCallable {
                sharedPreferences.edit().putString(key, value).commit()
            }
            .flatMapCompletable {
                if (it) {
                    Completable.complete()
                } else {
                    Completable.error(Throwable("Error while saving to shared preferences"))
                }
            }
    }
}