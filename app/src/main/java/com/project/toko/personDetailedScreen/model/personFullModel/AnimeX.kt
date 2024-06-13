package com.project.toko.personDetailedScreen.model.personFullModel
import com.google.gson.annotations.SerializedName

data class AnimeX(
    @SerializedName("images") val images: Images,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)