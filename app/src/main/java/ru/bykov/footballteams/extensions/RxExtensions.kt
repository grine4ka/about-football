package ru.bykov.footballteams.extensions

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.async(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> List<T>.toMaybe(): Maybe<List<T>> {
    return if (this.isEmpty()) {
        Maybe.empty()
    } else {
        Maybe.just(this)
    }
}

fun <T> T?.toMaybe(): Maybe<T> {
    return if (this == null) {
        Maybe.empty()
    } else {
        Maybe.just(this)
    }
}

inline fun <T> Maybe<T>.switchToSingleIfEmpty(other: () -> Single<T>): Single<T> {
    return switchIfEmpty(other())
}
