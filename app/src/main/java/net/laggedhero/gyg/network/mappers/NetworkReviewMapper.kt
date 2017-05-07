package net.laggedhero.gyg.network.mappers

import net.laggedhero.gyg.core.models.Review
import net.laggedhero.gyg.core.models.Reviewer
import net.laggedhero.gyg.network.models.NetworkReview

fun NetworkReview.toReview() = Review(
        review_id,
        rating,
        title,
        message,
        author,
        foreignLanguage,
        date,
        languageCode,
        Reviewer(reviewerName, reviewerCountry),
        traveler_type
)
