package com.example.animeapp.domain.staffModel

import com.google.gson.annotations.SerializedName

data class Data(
     @SerializedName("person") val person: Person,
     @SerializedName("positions") val positions: List<String>
)