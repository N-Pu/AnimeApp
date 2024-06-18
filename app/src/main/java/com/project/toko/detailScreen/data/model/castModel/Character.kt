package com.project.toko.detailScreen.data.model.castModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    @Expose @SerializedName("images") val images: com.project.toko.detailScreen.data.model.castModel.Images,
    @Expose @SerializedName("mal_id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)