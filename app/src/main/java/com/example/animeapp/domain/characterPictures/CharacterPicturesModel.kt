package com.example.animeapp.domain.characterPictures


import com.google.gson.annotations.SerializedName

data class CharacterPicturesModel(

   @SerializedName("data") val data: List<Data>
)