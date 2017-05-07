package net.laggedhero.gyg.network.models

data class NetworkReviewAggregator(val status: Boolean,
                                   val total_reviews: Long,
                                   val data: List<NetworkReview>?,
                                   val message: String?)
