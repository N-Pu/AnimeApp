package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName


data class Anime(
    @SerializedName("anime") val anime: com.project.toko.personDetailedScreen.data.model.personFullModel.AnimeX,
    @SerializedName("position") val position: String
)