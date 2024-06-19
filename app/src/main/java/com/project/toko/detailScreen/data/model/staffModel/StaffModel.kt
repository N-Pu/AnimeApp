package com.project.toko.detailScreen.data.model.staffModel
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
@Immutable
data class StaffModel(
    @SerializedName("data") val data: List<com.project.toko.detailScreen.data.model.staffModel.StaffData>
)