package net.laggedhero.gyg.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import net.laggedhero.gyg.R
import net.laggedhero.gyg.core.interactors.ReviewInteractor
import net.laggedhero.gyg.core.models.Review
import net.laggedhero.gyg.network.GygReviewApi
import net.laggedhero.gyg.network.repositories.NetworkReviewRepository
import net.laggedhero.gyg.realm.repositories.RealmReviewRepository
import net.laggedhero.gyg.scheduling.AppSchedulerProvider
import net.laggedhero.gyg.ui.Navigator

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val adapter = ReviewListAdapter()

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
        setUpPresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        presenter.release()
        super.onPause()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showReviews(reviews: List<Review>, hasMore: Boolean) {
        adapter.setReviewList(reviews, hasMore)
    }

    override fun showError(message: String) {
        Snackbar.make(reviewRecyclerView, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setUpView() {
        progressBar = findViewById(R.id.progressBar) as ProgressBar

        val layoutManager = LinearLayoutManager(this)

        reviewRecyclerView = findViewById(R.id.reviewList) as RecyclerView
        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewRecyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        reviewRecyclerView.adapter = adapter

        adapter.setOnLoadMoreListener(object : ReviewListAdapter.OnLoadMoreListener {
            override fun onLoadMore(page: Int) {
                presenter.onShowReviews(page)
            }
        })
    }

    private fun setUpPresenter() {
        presenter = MainPresenter(
                ReviewInteractor(NetworkReviewRepository(GygReviewApi.instance), RealmReviewRepository()),
                Navigator(this),
                AppSchedulerProvider(),
                getString(R.string.main_activity_fallback_error_message)
        )
    }
}
