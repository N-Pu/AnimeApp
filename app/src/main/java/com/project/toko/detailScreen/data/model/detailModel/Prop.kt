
package com.project.toko.detailScreen.data.model.detailModel
import com.google.gson.annotations.SerializedName
data class Prop(
    @SerializedName("from") val from: com.project.toko.detailScreen.data.model.detailModel.From,
    @SerializedName("string") val string: String,
    @SerializedName("to") val to: com.project.toko.detailScreen.data.model.detailModel.To
)