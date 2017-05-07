package net.laggedhero.gyg.network.repositories

import io.reactivex.Flowable
import io.reactivex.Single
import net.laggedhero.gyg.core.models.ReviewAggregator
import net.laggedhero.gyg.core.models.ReviewFilter
import net.laggedhero.gyg.core.repositories.ReviewRepository
import net.laggedhero.gyg.network.ReviewApi
import net.laggedhero.gyg.network.mappers.toReviewAggregator

class NetworkReviewRepository(val reviewApi: ReviewApi) : ReviewRepository {

    override fun retrieveReviews(reviewFilter: ReviewFilter): Flowable<ReviewAggregator> {
        return reviewApi.retrieveReviews(
                reviewFilter.city,
                reviewFilter.place,
                reviewFilter.count,
                reviewFilter.page,
                reviewFilter.rating,
                reviewFilter.type,
                reviewFilter.sortBy,
                reviewFilter.direction
        )
                .toFlowable()
                .map { it.toReviewAggregator() }
    }

    override fun saveReviews(reviewAggregator: ReviewAggregator): Single<Boolean> {
        return Single.just(true)
    }
}
