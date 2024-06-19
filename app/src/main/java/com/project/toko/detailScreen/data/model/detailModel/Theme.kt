
package com.project.toko.detailScreen.data.model.detailModel
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
@Immutable
data class Theme(
    @SerializedName("endings") val endings: List<String>,
    @SerializedName("openings") val openings: List<String>
)