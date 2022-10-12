package com.example.eurotest.data.remote.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("videos") var videos: ArrayList<VideosResponse> = arrayListOf(),
    @SerializedName("stories") var stories: ArrayList<StoriesResponse> = arrayListOf()
)
