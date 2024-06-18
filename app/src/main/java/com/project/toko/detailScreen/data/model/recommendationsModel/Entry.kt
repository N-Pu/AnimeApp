
package com.project.toko.detailScreen.data.model.recommendationsModel
import com.google.gson.annotations.SerializedName
data class Entry(
    @SerializedName("images") val images: com.project.toko.detailScreen.data.model.recommendationsModel.Images,
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)