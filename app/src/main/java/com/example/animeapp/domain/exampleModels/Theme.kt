package com.example.animeapp.domain.exampleModels
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class Theme(
     @SerializedName("mal_id") val mal_id: Int,
     @SerializedName("name") val name: String,
     @SerializedName("type") val type: String,
     @SerializedName("url") val url: String
)