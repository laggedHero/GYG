package net.laggedhero.gyg.realm.mappers

import net.laggedhero.gyg.core.models.Review
import net.laggedhero.gyg.core.models.Reviewer
import net.laggedhero.gyg.realm.models.RealmReview

fun RealmReview.toReview() = Review(
        reviewId,
        rating,
        title,
        message,
        author,
        isForeignLanguage,
        date,
        languageCode,
        Reviewer(reviewerName, reviewerCountry),
        travelerType
)

fun List<RealmReview>.toReviewList() = map { it.toReview() }

fun Review.toRealmReview() = RealmReview().let {
    it.reviewId = id
    it.rating = rating
    it.title = title
    it.message = message
    it.author = author
    it.isForeignLanguage = isForeignLanguage
    it.date = date
    it.languageCode = languageCode
    it.reviewerName = reviewer.name
    it.reviewerCountry = reviewer.country
    it.travelerType = travelerType

    it
}

fun List<Review>.toRealmReviewList() = map { it.toRealmReview() }