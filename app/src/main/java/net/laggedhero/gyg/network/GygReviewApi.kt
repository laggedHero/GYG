package net.laggedhero.gyg.network

import net.laggedhero.gyg.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class GygReviewApi {

    companion object {

        val instance by lazy<ReviewApi> {
            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.getyourguide.com")

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.HEADERS

                val client = OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build()

                builder.client(client)
            }

            builder.build()
                    .create(ReviewApi::class.java)
        }
    }
}
