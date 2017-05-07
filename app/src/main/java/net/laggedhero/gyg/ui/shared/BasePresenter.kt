package net.laggedhero.gyg.ui.shared

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T> {

    protected var view: T? = null
    private val compositeDisposable = CompositeDisposable()

    override fun bindView(view: T) {
        this.view = view
    }

    override fun release() {
        compositeDisposable.clear()
        view = null
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearDisposables() {
        compositeDisposable.clear()
    }
}
