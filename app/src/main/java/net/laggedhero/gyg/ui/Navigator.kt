package net.laggedhero.gyg.ui

import android.content.Context
import android.content.Intent
import net.laggedhero.gyg.ui.newreview.NewReviewActivity

class Navigator(var context: Context) {

    init {
        context = context.applicationContext
    }

    fun toNewReview() {
        context.startActivity(
                Intent(context, NewReviewActivity::class.java)
        )
    }
}
