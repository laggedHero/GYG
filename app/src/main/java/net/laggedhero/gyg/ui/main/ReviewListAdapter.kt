package net.laggedhero.gyg.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.laggedhero.gyg.core.models.Review

class ReviewListAdapter : RecyclerView.Adapter<ReviewListItemViewHolder>() {

    private val reviewList = mutableListOf<Review>()
    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var page = 0

    fun setReviewList(reviewList: List<Review>, hasMore: Boolean) {
        if (!hasMore) {
            page = -1
        }

        this.reviewList.clear()
        this.reviewList.addAll(reviewList)
        notifyDataSetChanged()
    }

    fun resetPaging() {
        page = 0
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    override fun getItemCount() = reviewList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ReviewListItemViewHolder.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: ReviewListItemViewHolder, position: Int) {
        if (position == reviewList.size - 1 && page != -1) {
            onLoadMoreListener?.onLoadMore(++page)
        }

        return ReviewListItemViewHolder.onBindViewHolder(holder, reviewList[position])
    }

    interface OnLoadMoreListener {
        fun onLoadMore(page: Int)
    }
}
