package net.laggedhero.gyg.realm.repositories

import android.os.Handler
import android.os.Looper
import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.RealmResults
import net.laggedhero.gyg.core.models.ReviewAggregator
import net.laggedhero.gyg.core.models.ReviewFilter
import net.laggedhero.gyg.core.repositories.ReviewRepository
import net.laggedhero.gyg.realm.mappers.toRealmReviewList
import net.laggedhero.gyg.realm.mappers.toReviewList
import net.laggedhero.gyg.realm.models.RealmReview

class RealmReviewRepository : ReviewRepository {

    override fun retrieveReviews(reviewFilter: ReviewFilter): Flowable<ReviewAggregator> {
        return Flowable.defer<ReviewAggregator> {
            val realm = Realm.getDefaultInstance()
            val looper = Looper.myLooper()

            if (realm == null || looper == null) {
                return@defer Flowable.just(ReviewAggregator())
            }

            Flowable.using<RealmResults<RealmReview>, Pair<Realm, Looper>>(
                    { Pair(realm, looper) },
                    {
                        RxJavaInterop.toV2Flowable(
                                it.first.where(RealmReview::class.java)
                                        .findAll()
                                        .asObservable())
                    },
                    { Handler(it.second).post { it.first.close() } }
            )
                    .unsubscribeOn(AndroidSchedulers.from(looper))
                    .filter { it.isLoaded && it.isValid }
                    .map { ReviewAggregator(true, it.size.toLong(), it.toReviewList()) }
        }
    }

    override fun saveReviews(reviewAggregator: ReviewAggregator): Single<Boolean> {
        return Single.defer<Boolean> {
            val realm = Realm.getDefaultInstance()
            val looper = Looper.myLooper()

            if (realm == null || looper == null) {
                return@defer Single.just(false)
            }

            Single.using<Boolean, Pair<Realm, Looper>>(
                    { Pair(realm, looper) },
                    {
                        Single.create<Boolean> {
                            if (it.isDisposed) {
                                return@create
                            }

                            val realmReviewList = reviewAggregator.reviews.toRealmReviewList()

                            if (it.isDisposed) {
                                return@create
                            }

                            realm.beginTransaction()
                            realm.copyToRealmOrUpdate(realmReviewList)
                            realm.commitTransaction()

                            it.onSuccess(true)
                        }
                    },
                    { Handler(it.second).post { it.first.close() } }
            )
                    .unsubscribeOn(AndroidSchedulers.from(looper))
        }
    }
}
