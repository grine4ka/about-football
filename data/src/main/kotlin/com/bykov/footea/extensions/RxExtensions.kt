package com.bykov.footea.extensions

import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

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
