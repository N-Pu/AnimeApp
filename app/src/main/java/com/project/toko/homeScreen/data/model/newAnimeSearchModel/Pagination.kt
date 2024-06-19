package com.project.toko.homeScreen.data.model.newAnimeSearchModel
import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("has_next_page") val has_next_page: Boolean,
    @SerializedName("items") val items: com.project.toko.homeScreen.data.model.newAnimeSearchModel.Items,
    @SerializedName("last_visible_page") val last_visible_page: Int
)