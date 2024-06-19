package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class NewAnimeSearchModel(
    @SerializedName("data") val data: List<com.project.toko.homeScreen.data.model.newAnimeSearchModel.AnimeSearchData>,
    @SerializedName("pagination") val pagination: com.project.toko.homeScreen.data.model.newAnimeSearchModel.Pagination
)