package com.project.toko.characterDetailedScreen.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class Webp(
    @SerializedName("image_url") val image_url: String,
    @SerializedName("large_image_url") val large_image_url: String,
    @SerializedName("small_image_url") val small_image_url: String
)