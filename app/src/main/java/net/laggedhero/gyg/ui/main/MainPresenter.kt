package net.laggedhero.gyg.ui.main

import net.laggedhero.gyg.core.interactors.ReviewInteractor
import net.laggedhero.gyg.core.models.ReviewFilter
import net.laggedhero.gyg.scheduling.SchedulerProvider
import net.laggedhero.gyg.ui.Navigator
import net.laggedhero.gyg.ui.shared.BasePresenter

class MainPresenter(val reviewInteractor: ReviewInteractor,
                    val navigator: Navigator,
                    val schedulerProvider: SchedulerProvider,
                    val fallbackErrorMessage: String) : MainContract.Presenter, BasePresenter<MainContract.View>() {

    private var reviewFilter = ReviewFilter("berlin-l17", "tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776")

    override fun bindView(view: MainContract.View) {
        super.bindView(view)

        onShowReviews(0)
    }

    override fun onShowReviews(page: Int) {
        view?.showLoading()

        val disposable = reviewInteractor.retrieveReviews(reviewFilter.copy(page = page))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { (status, _, reviews, message) ->
                            view?.hideLoading()

                            view?.showReviews(reviews, status)

                            if (!status) {
                                view?.showError(message ?: fallbackErrorMessage)
                            }
                        },
                        {
                            view?.hideLoading()
                            view?.showError(it.message ?: fallbackErrorMessage)
                        }
                )

        addDisposable(disposable)
    }

    override fun onNewReview() {
        navigator.toNewReview()
    }
}
