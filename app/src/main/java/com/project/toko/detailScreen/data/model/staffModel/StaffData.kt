package com.project.toko.detailScreen.data.model.staffModel

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class StaffData(
    @SerializedName("person") val person: com.project.toko.detailScreen.data.model.staffModel.Person,
    @SerializedName("positions") val positions: List<String>
)