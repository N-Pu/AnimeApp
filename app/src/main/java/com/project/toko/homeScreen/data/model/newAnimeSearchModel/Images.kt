package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg") val jpg: com.project.toko.homeScreen.data.model.newAnimeSearchModel.Jpg,
    @SerializedName("webp") val webp: com.project.toko.homeScreen.data.model.newAnimeSearchModel.Webp
)