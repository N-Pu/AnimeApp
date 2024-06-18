package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from") val from: com.project.toko.homeScreen.data.model.newAnimeSearchModel.From,
    @SerializedName("string") val string: String,
    @SerializedName("to") val to: com.project.toko.homeScreen.data.model.newAnimeSearchModel.To
)