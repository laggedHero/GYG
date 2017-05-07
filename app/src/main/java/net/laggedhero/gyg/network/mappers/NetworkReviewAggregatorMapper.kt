package net.laggedhero.gyg.network.mappers

import net.laggedhero.gyg.core.models.ReviewAggregator
import net.laggedhero.gyg.network.models.NetworkReviewAggregator

fun NetworkReviewAggregator.toReviewAggregator() = ReviewAggregator(
        status,
        total_reviews,
        data?.map { it.toReview() } ?: listOf(),
        message
)

