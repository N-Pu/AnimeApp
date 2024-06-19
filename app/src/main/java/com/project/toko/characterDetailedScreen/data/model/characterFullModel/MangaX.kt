package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class MangaX(
    @SerializedName("images") val images: com.project.toko.characterDetailedScreen.data.model.characterFullModel.ImagesXX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)