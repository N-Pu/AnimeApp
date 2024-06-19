package com.project.toko.personDetailedScreen.data.model.personFullModel
import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class Data(
    @SerializedName("about") val about: String,
    @SerializedName("alternate_names") val alternate_names: List<String>,
    @SerializedName("anime") val anime: List<com.project.toko.personDetailedScreen.data.model.personFullModel.Anime>,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("family_name") val family_name: String,
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("given_name") val given_name: String,
    @SerializedName("images") val images: com.project.toko.personDetailedScreen.data.model.personFullModel.ImagesX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("manga") val manga: List<com.project.toko.personDetailedScreen.data.model.personFullModel.Manga>,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("voices") val voices: List<com.project.toko.personDetailedScreen.data.model.personFullModel.Voice>,
    @SerializedName("website_url") val website_url: String
)