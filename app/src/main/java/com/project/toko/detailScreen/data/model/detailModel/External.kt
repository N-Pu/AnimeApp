
package com.project.toko.detailScreen.data.model.detailModel
import com.google.gson.annotations.SerializedName
data class External(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)