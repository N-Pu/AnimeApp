
package com.project.toko.detailScreen.data.model.detailModel
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
@Immutable
data class Relation(
    @SerializedName("entry") val entry: List<com.project.toko.detailScreen.data.model.detailModel.Entry>,
    @SerializedName("relation") val relation: String
)