package net.laggedhero.gyg.network

import io.reactivex.Single
import net.laggedhero.gyg.network.models.NetworkNewReview
import net.laggedhero.gyg.network.models.NetworkReview
import net.laggedhero.gyg.network.models.NetworkReviewAggregator
import retrofit2.http.*

interface ReviewApi {

    @Headers(
            "user-agent: Chrome/57.0.2987.133"
    )
    @GET("/{city}/{place}/reviews.json")
    fun retrieveReviews(@Path("city") city: String,
                        @Path("place") place: String,
                        @Query("count") count: Int,
                        @Query("page") page: Int,
                        @Query("rating") rating: Int,
                        @Query("type") type: String?,
                        @Query("sortBy") sortBy: String,
                        @Query("direction") direction: String): Single<NetworkReviewAggregator>

    @POST("/{city}/{place}/reviews")
    fun addReview(@Path("city") city: String,
                  @Path("place") place: String,
                  @Body networkNewReview: NetworkNewReview): Single<NetworkReview>
}
