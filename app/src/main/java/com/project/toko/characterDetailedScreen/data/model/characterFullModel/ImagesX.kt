
package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class ImagesX(
    @SerializedName("jpg") val jpg: com.project.toko.characterDetailedScreen.data.model.characterFullModel.JpgX,
    @SerializedName("webp") val webp: com.project.toko.characterDetailedScreen.data.model.characterFullModel.WebpX
)