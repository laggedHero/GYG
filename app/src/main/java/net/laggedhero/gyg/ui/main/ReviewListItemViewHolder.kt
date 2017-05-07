package net.laggedhero.gyg.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.laggedhero.gyg.R
import net.laggedhero.gyg.core.models.Review

class ReviewListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.findViewById(R.id.title) as TextView
    private val dateTextView = itemView.findViewById(R.id.date) as TextView
    private val ratingTextView = itemView.findViewById(R.id.rating) as TextView
    private val messageTextView = itemView.findViewById(R.id.message) as TextView
    private val authorTextView = itemView.findViewById(R.id.author) as TextView

    companion object {
        fun onCreateViewHolder(parent: ViewGroup): ReviewListItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.review_list_item, parent, false)

            return ReviewListItemViewHolder(view)
        }

        fun onBindViewHolder(holder: ReviewListItemViewHolder, review: Review) {
            holder.bind(review)
        }
    }

    fun bind(review: Review) {
        val resources = itemView.resources

        titleTextView.text = resources.getString(R.string.review_list_item_title_format, review.title)
        dateTextView.text = review.date
        ratingTextView.text = resources.getString(R.string.review_list_item_rating_format, review.rating)
        messageTextView.text = review.message
        authorTextView.text = resources.getString(R.string.review_list_item_author_format, review.author)
    }
}
