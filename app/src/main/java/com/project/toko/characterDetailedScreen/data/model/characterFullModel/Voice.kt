package com.project.toko.characterDetailedScreen.data.model.characterFullModel
import com.google.gson.annotations.SerializedName


data class Voice(
    @SerializedName("language") val language: String,
    @SerializedName("person") val person: com.project.toko.characterDetailedScreen.data.model.characterFullModel.Person
)