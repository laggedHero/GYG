package net.laggedhero.gyg.ui.main

import net.laggedhero.gyg.core.models.Review
import net.laggedhero.gyg.ui.shared.BaseContract

interface MainContract {

    interface View : BaseContract.View {

        fun showLoading()

        fun hideLoading()

        fun showReviews(reviews: List<Review>, hasMore: Boolean)

        fun showError(message: String)

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun onShowReviews(page: Int)

        fun onNewReview()
    }
}
