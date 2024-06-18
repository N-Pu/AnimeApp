
package com.project.toko.detailScreen.data.model.detailModel
import com.google.gson.annotations.SerializedName
data class To(
    @SerializedName("day") val day: Int,
    @SerializedName("month") val month: Int,
    @SerializedName("year") val year: Int
)