package net.laggedhero.gyg.core.interactors

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import net.laggedhero.gyg.core.models.ReviewAggregator
import net.laggedhero.gyg.core.models.ReviewFilter
import net.laggedhero.gyg.core.repositories.ReviewRepository

class ReviewInteractor(val sourceReviewRepository: ReviewRepository,
                       val localReviewRepository: ReviewRepository) {

    fun retrieveReviews(reviewFilter: ReviewFilter): Flowable<ReviewAggregator> {

        return sourceReviewRepository.retrieveReviews(reviewFilter)
                .flatMap { localReviewRepository.saveReviews(it).toFlowable() }
                .flatMap { localReviewRepository.retrieveReviews(reviewFilter) }
                .onErrorResumeNext { t: Throwable ->
                    Flowable.zip<Throwable, ReviewAggregator, ReviewAggregator>(
                            Flowable.just(t),
                            localReviewRepository.retrieveReviews(reviewFilter),
                            BiFunction { throwable, reviewAgg ->
                                reviewAgg.copy(status = false, message = throwable.message)
                            }
                    )
                }
    }
}
