package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("images") val images: com.project.toko.characterDetailedScreen.data.model.characterFullModel.ImagesXXX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)