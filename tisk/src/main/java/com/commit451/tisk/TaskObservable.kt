package com.commit451.tisk

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins

class TaskObservable<T>(private val task: Task<T>) : Observable<T>() {

    override fun subscribeActual(observer: Observer<in T>) {

        val disposable = DumbDisposable()
        observer.onSubscribe(disposable)

        var terminated = false
        try {
            val response = Tasks.await(task)
            if (!disposable.isDisposed) {
                observer.onNext(response)
            }
            if (!disposable.isDisposed) {
                terminated = true
                observer.onComplete()
            }
        } catch (t: Throwable) {
            Exceptions.throwIfFatal(t)
            if (terminated) {
                RxJavaPlugins.onError(t)
            } else if (!disposable.isDisposed) {
                try {
                    observer.onError(t)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(t, inner))
                }
            }
        }
    }

    private class DumbDisposable internal constructor() : Disposable {

        private var canceled = false

        override fun dispose() {
            canceled = true
        }

        override fun isDisposed(): Boolean {
            return canceled
        }
    }
}