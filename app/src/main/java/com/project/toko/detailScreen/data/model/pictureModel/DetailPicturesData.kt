package com.project.toko.detailScreen.data.model.pictureModel

import com.google.gson.annotations.SerializedName

data class DetailPicturesData(
    @SerializedName("jpg") val jpg: com.project.toko.detailScreen.data.model.pictureModel.Jpg,
    @SerializedName("webp") val webp: com.project.toko.detailScreen.data.model.pictureModel.Webp
)