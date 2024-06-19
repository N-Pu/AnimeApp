
package com.project.toko.characterDetailedScreen.data.model.characterFullModel

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class Data(
    @SerializedName("about") val about: String,
    @SerializedName("anime") val anime: List<com.project.toko.characterDetailedScreen.data.model.characterFullModel.Anime>,
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("images") val images: com.project.toko.characterDetailedScreen.data.model.characterFullModel.ImagesX,
    @SerializedName("mal_id") val mal_id: Int,
    @SerializedName("manga") val manga: List<com.project.toko.characterDetailedScreen.data.model.characterFullModel.Manga>,
    @SerializedName("name") val name: String,
    @SerializedName("name_kanji") val name_kanji: String,
    @SerializedName("nicknames") val nicknames: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("voices") val voices: List<com.project.toko.characterDetailedScreen.data.model.characterFullModel.Voice>
)