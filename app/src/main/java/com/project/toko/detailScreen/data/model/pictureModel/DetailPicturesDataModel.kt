package com.project.toko.detailScreen.data.model.pictureModel

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
@Immutable
data class DetailPicturesDataModel(
    @SerializedName("data") val data: List<com.project.toko.detailScreen.data.model.pictureModel.DetailPicturesData>
)