package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName

data class AnimeX(
    @SerializedName("images") val images: com.project.toko.personDetailedScreen.data.model.personFullModel.Images,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)