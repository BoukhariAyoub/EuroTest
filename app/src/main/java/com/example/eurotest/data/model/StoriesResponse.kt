package com.example.eurotest.data.model

import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("teaser") var teaser: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("date") var date: Double? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("sport") var sport: SportResponse? = SportResponse()
)