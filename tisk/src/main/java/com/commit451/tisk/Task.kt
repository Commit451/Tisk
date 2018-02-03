package com.commit451.tisk

import com.google.android.gms.tasks.Task
import io.reactivex.*

/**
 * Converts the task to an observable which will emit the result, then complete
 */
fun <T> Task<T>.toObservable(): Observable<T> {
    return TaskObservable(this)
}

/**
 * Converts the call to a flowable which will emit the latest response, then complete
 */
fun <T> Task<T>.toFlowable(): Flowable<T> {
    return toObservable().toFlowable(BackpressureStrategy.LATEST)
}

/**
 * Converts the call to a single which will emit the response or an error
 */
fun <T> Task<T>.toSingle(): Single<T> {
    return toObservable().singleOrError()
}

/**
 * Converts the call to a completable which will complete, or fail with an exception
 */
fun <T> Task<T>.toCompletable(): Completable {
    return toObservable().flatMapCompletable { _ ->
        //If we get a result, it completed
        Completable.complete()
    }
}