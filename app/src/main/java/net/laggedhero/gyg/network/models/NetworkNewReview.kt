package net.laggedhero.gyg.network.models

data class NetworkNewReview(val rating: String,
                            val title: String,
                            val message: String,
                            val date: Long,
                            val languageCode: String,
                            val reviewerName: String,
                            val reviewerCountry: String,
                            val traveler_type: String?)
