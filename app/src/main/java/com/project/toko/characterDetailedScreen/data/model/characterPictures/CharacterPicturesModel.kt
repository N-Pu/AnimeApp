package com.project.toko.characterDetailedScreen.data.model.characterPictures


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class CharacterPicturesModel(

   @SerializedName("data") val data: List<com.project.toko.characterDetailedScreen.data.model.characterPictures.CharacterPicturesData>
)