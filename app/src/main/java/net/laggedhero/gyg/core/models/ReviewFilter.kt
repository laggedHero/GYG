package net.laggedhero.gyg.core.models

data class ReviewFilter(val city: String,
                        val place: String,
                        val count: Int = 5,
                        val page: Int = 0,
                        val rating: Int = 0,
                        val sortBy: String = "date_of_review",
                        val direction: String = "DESC",
                        val type: String = "")
