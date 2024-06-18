package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import com.google.gson.annotations.SerializedName

data class To(
    @SerializedName("day") val day: Int,
    @SerializedName("month") val month: Int,
    @SerializedName("year") val year: Int
)