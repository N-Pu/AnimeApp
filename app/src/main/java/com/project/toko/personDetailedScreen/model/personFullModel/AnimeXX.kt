package com.project.toko.personDetailedScreen.model.personFullModel
import com.google.gson.annotations.SerializedName

data class AnimeXX(
    @SerializedName("images") val images: ImagesXXX,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)