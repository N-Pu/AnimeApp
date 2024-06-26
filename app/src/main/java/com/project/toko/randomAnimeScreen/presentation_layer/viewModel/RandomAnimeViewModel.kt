package com.project.toko.randomAnimeScreen.presentation_layer.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.project.toko.core.domain.repository.MalApiService
import com.project.toko.core.domain.util.connectionCheck.CommonFunction
import com.project.toko.core.domain.util.stateCheck.CheckState
import com.project.toko.homeScreen.data.model.newAnimeSearchModel.AnimeSearchData
import com.project.toko.homeScreen.domain.useCase.NsfwDataProvider
import com.project.toko.randomAnimeScreen.data.model.AnimeRandomModel
import com.project.toko.randomAnimeScreen.domain.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class RandomAnimeViewModel @Inject constructor(
//    @ApplicationContext val context: Context,
//    private val malApiService: MalApiService
//) :
//    ViewModel() {
//    private var isSearching = false
//    private val _animeDetails = MutableStateFlow<AnimeSearchData?>(null)
//    val animeDetails: StateFlow<AnimeSearchData?> get() = _animeDetails
//
//
//    private val _cardIsShown = mutableStateOf(false)
//    val cardIsShown = _cardIsShown
//
//    val isNSFWActive = NsfwDataProvider(context).isNSFWActive
//
//    suspend fun onTapRandomAnime() {
//        if (isSearching) return
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                isSearching = true
//                val response = malApiService.getRandomAnime(!isNSFWActive.value)
//                if (response.isSuccessful) {
//                    val data = response.body()?.data
//                    if (data != null) {
//                        _animeDetails.value = data
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("RandomAnimeViewModel", e.toString())
//            } finally {
//                isSearching = false
//            }
//        }
//    }
//}

@HiltViewModel
class RandomAnimeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val malApiService: MalApiService,
    private val randomRepository: RandomRepository
) :
    ViewModel() {

    private val _response: MutableStateFlow<CheckState<AnimeRandomModel?>> =
        MutableStateFlow(CheckState.Loading)
    val randomResponse: StateFlow<CheckState<AnimeRandomModel?>> = _response

    init {
        fetchRandomAnime()
    }

    fun fetchRandomAnime() {
        viewModelScope.launch {
            if (CommonFunction.isNetworkAvailable(context)) {
                try {
                    val response = randomRepository.getRandomAnime().first()
                    _response.emit(CheckState.Success(response))
                } catch (e: Exception) {
                    val errorMessage = "An error occurred. Please try again."
                    Toast
                        .makeText(
                            context,
                            errorMessage,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    _response.emit(CheckState.Error(errorMessage))
                }
            } else {
                val connectionError = "No internet connection"
                _response.emit(CheckState.Error(connectionError))

                Toast
                    .makeText(
                        context,
                        connectionError,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

}
