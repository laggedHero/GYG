package net.laggedhero.gyg.core.models

data class ReviewAggregator(val status: Boolean = false,
                            val total: Long = 0,
                            val reviews: List<Review> = listOf(),
                            val message: String? = null)
