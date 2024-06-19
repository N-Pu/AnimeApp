package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName

data class Voice(
    @SerializedName("anime") val anime: com.project.toko.personDetailedScreen.data.model.personFullModel.AnimeXX,
    @SerializedName("character") val character: com.project.toko.personDetailedScreen.data.model.personFullModel.Character,
    @SerializedName("role") val role: String
)