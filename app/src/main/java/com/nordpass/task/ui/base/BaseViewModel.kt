package com.nordpass.task.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    val error = SingleLiveEvent<ErrorMessage>()

    protected fun Disposable.attach() {
        disposables.add(this)
    }

    protected open fun handleError(error: Throwable) {
        Timber.e(error, "Error during processing")
        this.error.postValue(ErrorMessageMapper.map(error))
    }

    override fun onCleared() {
        disposables.clear()
    }
}