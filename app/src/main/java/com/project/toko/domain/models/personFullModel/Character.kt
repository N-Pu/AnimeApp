package com.project.toko.domain.models.personFullModel
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("images") val images: ImagesXXXX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)