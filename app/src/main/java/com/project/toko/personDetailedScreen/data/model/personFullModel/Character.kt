package com.project.toko.personDetailedScreen.data.model.personFullModel
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("images") val images: com.project.toko.personDetailedScreen.data.model.personFullModel.ImagesXXXX,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)