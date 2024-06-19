package com.project.toko.detailScreen.data.model.castModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(
    @Expose @SerializedName("jpg") val jpg: com.project.toko.detailScreen.data.model.castModel.Jpg,
    @Expose @SerializedName("webp") val webp: com.project.toko.detailScreen.data.model.castModel.Webp
)