
package com.project.toko.detailScreen.data.model.detailModel
import com.google.gson.annotations.SerializedName
data class Images(
    @SerializedName("jpg") val jpg: com.project.toko.detailScreen.data.model.detailModel.Jpg,
    @SerializedName("webp") val webp: com.project.toko.detailScreen.data.model.detailModel.Webp
)