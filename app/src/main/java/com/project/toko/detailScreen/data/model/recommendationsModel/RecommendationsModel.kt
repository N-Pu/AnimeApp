package com.project.toko.detailScreen.data.model.recommendationsModel

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
@Immutable
data class RecommendationsModel(
    @SerializedName("data") val data: List<com.project.toko.detailScreen.data.model.recommendationsModel.RecommendationsData>
)