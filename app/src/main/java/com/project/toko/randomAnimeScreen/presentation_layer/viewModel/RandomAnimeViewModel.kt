package com.project.toko.randomAnimeScreen.presentation_layer.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.toko.core.domain.repository.MalApiService
import com.project.toko.homeScreen.data.model.newAnimeSearchModel.AnimeSearchData
import com.project.toko.homeScreen.domain.useCase.NsfwDataProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomAnimeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val malApiService: MalApiService
) :
    ViewModel() {
    private var isSearching = false
    private val _animeDetails = MutableStateFlow<AnimeSearchData?>(null)
    val animeDetails: StateFlow<AnimeSearchData?> get() = _animeDetails


    private val _cardIsShown = mutableStateOf(false)
    val cardIsShown = _cardIsShown

    val isNSFWActive = NsfwDataProvider(context).isNSFWActive

    suspend fun onTapRandomAnime() {
        if (isSearching) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isSearching = true
                val response = malApiService.getRandomAnime(!isNSFWActive.value)
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        _animeDetails.value = data
                    }
                }
            } catch (e: Exception) {
                Log.e("RandomAnimeViewModel", e.toString())
            } finally {
                isSearching = false
            }
        }
    }
}
