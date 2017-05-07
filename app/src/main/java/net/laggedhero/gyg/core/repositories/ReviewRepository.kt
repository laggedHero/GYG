package net.laggedhero.gyg.core.repositories

import io.reactivex.Flowable
import io.reactivex.Single
import net.laggedhero.gyg.core.models.ReviewAggregator
import net.laggedhero.gyg.core.models.ReviewFilter

interface ReviewRepository {

    fun retrieveReviews(reviewFilter: ReviewFilter): Flowable<ReviewAggregator>

    fun saveReviews(reviewAggregator: ReviewAggregator): Single<Boolean>
}
