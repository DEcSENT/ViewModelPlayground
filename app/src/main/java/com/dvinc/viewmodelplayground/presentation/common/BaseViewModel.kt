package com.dvinc.viewmodelplayground.presentation.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
