package com.project.toko.homeScreen.domain.useCase

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val _isNSFWActive = mutableStateOf(false)

class NsfwDataProvider @Inject constructor(@ApplicationContext val context: Context) {

    val isNSFWActive = _isNSFWActive

    fun saveNSFWData(isActive: Boolean) {
        val sharedPreferences = context.getSharedPreferences("NSFW Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("NSFW_MODE", isActive)
        editor.apply()
        _isNSFWActive.value = isActive
    }

    fun loadNSFWData() {
        val sharedPreferences = context.getSharedPreferences("NSFW Mode", Context.MODE_PRIVATE)
        _isNSFWActive.value = sharedPreferences.getBoolean("NSFW_MODE", false)
    }
}