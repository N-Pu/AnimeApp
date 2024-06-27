package com.project.toko.randomAnimeScreen.presentation_layer.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.project.toko.core.domain.repository.MalApiService
import com.project.toko.core.domain.util.connectionCheck.CommonFunction
import com.project.toko.core.domain.util.stateCheck.CheckState
import com.project.toko.randomAnimeScreen.data.model.AnimeRandomModel
import com.project.toko.randomAnimeScreen.domain.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomAnimeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val randomRepository: RandomRepository
) :
    ViewModel() {
    init {
        fetchRandomAnime()
    }

    private val _response: MutableStateFlow<CheckState<AnimeRandomModel?>> =
        MutableStateFlow(CheckState.Loading)
    val randomResponse: StateFlow<CheckState<AnimeRandomModel?>> = _response

    fun fetchRandomAnime() {
        viewModelScope.launch {
            if (CommonFunction.isNetworkAvailable(context)) {
                try {
                    val response = randomRepository.getRandomAnime().first()
                    _response.emit(CheckState.Success(response))
                } catch (e: Exception) {
                    val errorMessage = "An error occurred. Please try again."
                    _response.emit(CheckState.Error(errorMessage))

                    Toast
                        .makeText(
                            context,
                            errorMessage,
                            Toast.LENGTH_SHORT
                        )
                        .show()
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
