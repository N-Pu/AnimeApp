package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName

data class Manga(
    @SerializedName("manga") val manga: com.project.toko.personDetailedScreen.data.model.personFullModel.MangaX,
    @SerializedName("position") val position: String
)