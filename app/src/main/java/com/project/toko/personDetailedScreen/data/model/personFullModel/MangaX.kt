package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName

data class MangaX(
    @SerializedName("images") val images: com.project.toko.personDetailedScreen.data.model.personFullModel.ImagesXX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)