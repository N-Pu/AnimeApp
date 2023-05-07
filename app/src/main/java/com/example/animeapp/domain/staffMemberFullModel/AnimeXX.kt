package com.example.animeapp.domain.staffMemberFullModel
import com.google.gson.annotations.SerializedName

data class AnimeXX(
    @SerializedName("images") val images: ImagesXXX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)