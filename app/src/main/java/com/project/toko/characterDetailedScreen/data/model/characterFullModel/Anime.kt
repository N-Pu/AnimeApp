package com.project.toko.characterDetailedScreen.data.model.characterFullModel

import com.google.gson.annotations.SerializedName


data class Anime(
    @SerializedName("anime") val anime: com.project.toko.characterDetailedScreen.data.model.characterFullModel.AnimeX,
    @SerializedName("role") val role: String
)