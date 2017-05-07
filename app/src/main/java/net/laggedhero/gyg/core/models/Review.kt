package net.laggedhero.gyg.core.models

data class Review(val id: Long,
                  val rating: String,
                  val title: String,
                  val message: String,
                  val author: String,
                  val isForeignLanguage: Boolean,
                  val date: String,
                  val languageCode: String,
                  val reviewer: Reviewer,
                  val travelerType: String?)