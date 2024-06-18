package com.project.toko.detailScreen.data.model.staffModel
import com.google.gson.annotations.SerializedName
data class Person(
    @SerializedName("images") val images: com.project.toko.detailScreen.data.model.staffModel.Images,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)