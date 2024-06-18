
package com.project.toko.detailScreen.data.model.detailModel
import com.google.gson.annotations.SerializedName
data class Aired(
    @SerializedName("from") val from: String,
    @SerializedName("prop") val prop: com.project.toko.detailScreen.data.model.detailModel.Prop,
    @SerializedName("to") val to: String
)