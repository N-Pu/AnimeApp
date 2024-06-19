package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class Images(
    @SerializedName("jpg") val jpg: com.project.toko.characterDetailedScreen.data.model.characterFullModel.Jpg,
    @SerializedName("webp") val webp: com.project.toko.characterDetailedScreen.data.model.characterFullModel.Webp
)