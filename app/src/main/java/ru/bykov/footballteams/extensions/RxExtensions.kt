package ru.bykov.footballteams.extensions

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

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

fun <T> Single<T>.retryExponential(times: Int = 3): Single<T> {
    return retryWhen { errors ->
        val counter = AtomicInteger()
        return@retryWhen errors
            .takeWhile { counter.getAndIncrement() != times }
            .flatMap {
                val delay = 2.toDouble().pow(counter.get().toDouble()).toLong()
                Flowable.timer(delay, TimeUnit.SECONDS)
            }
    }
}

inline fun <T> Maybe<T>.switchToSingleIfEmpty(other: () -> Single<T>): Single<T> {
    return switchIfEmpty(other())
}
