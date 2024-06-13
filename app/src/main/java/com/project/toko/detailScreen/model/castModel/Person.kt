package com.project.toko.detailScreen.model.castModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class Person(
    @Expose @SerializedName("images") val images: ImagesX,
    @Expose @SerializedName("mal_id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)