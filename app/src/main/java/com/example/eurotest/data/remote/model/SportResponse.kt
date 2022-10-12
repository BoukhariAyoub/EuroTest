package com.example.eurotest.data.remote.model

import com.google.gson.annotations.SerializedName

data class SportResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)