package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class Manga(
    @SerializedName("manga") val manga: com.project.toko.characterDetailedScreen.data.model.characterFullModel.MangaX,
    @SerializedName("role") val role: String
)