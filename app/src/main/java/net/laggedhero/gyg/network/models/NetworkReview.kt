package net.laggedhero.gyg.network.models

class NetworkReview(val review_id: Long,
                    val rating: String,
                    val title: String,
                    val message: String,
                    val author: String,
                    val foreignLanguage: Boolean,
                    val date: String,
                    val languageCode: String,
                    val reviewerName: String,
                    val reviewerCountry: String,
                    val traveler_type: String?)
