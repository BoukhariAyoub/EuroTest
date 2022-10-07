package com.example.eurotest.data.model

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("thumb") var thumb: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("date") var date: Double? = null,
    @SerializedName("sport") var sport: SportResponse? = SportResponse(),
    @SerializedName("views") var views: Int? = null
)
