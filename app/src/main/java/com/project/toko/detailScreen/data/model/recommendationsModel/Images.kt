
package com.project.toko.detailScreen.data.model.recommendationsModel
import com.google.gson.annotations.SerializedName
data class Images(
    @SerializedName("jpg") val jpg: com.project.toko.detailScreen.data.model.recommendationsModel.Jpg,
    @SerializedName("webp") val webp: com.project.toko.detailScreen.data.model.recommendationsModel.Webp
)