package com.project.toko.detailScreen.data.model.castModel

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Immutable
data class CastData(
    @Expose @SerializedName("character") val character: com.project.toko.detailScreen.data.model.castModel.Character,
    @Expose @SerializedName("role") val role: String,
    @Expose @SerializedName("voice_actors") val voice_actors: List<com.project.toko.detailScreen.data.model.castModel.VoiceActor>
)