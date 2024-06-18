package com.project.toko.randomAnimeScreen.data.model


import com.google.gson.annotations.SerializedName
import com.project.toko.homeScreen.data.model.newAnimeSearchModel.AnimeSearchData

data class AnimeRandomModel(
     @SerializedName("data") val data: AnimeSearchData
)