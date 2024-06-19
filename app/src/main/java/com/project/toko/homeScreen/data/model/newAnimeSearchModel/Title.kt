package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import com.google.gson.annotations.SerializedName
data class Title(
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String
)