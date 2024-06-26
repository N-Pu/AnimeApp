package com.project.toko.randomAnimeScreen.domain.repository

import android.content.Context
import com.project.toko.core.domain.repository.MalApiService
import com.project.toko.homeScreen.domain.useCase.NsfwDataProvider
import com.project.toko.randomAnimeScreen.data.model.AnimeRandomModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RandomRepository @Inject constructor(
    private val apiService: MalApiService,
    @ApplicationContext val context: Context
) {

    fun getRandomAnime(): Flow<AnimeRandomModel?> = flow {
        val isNSFWActive = NsfwDataProvider(context).isNSFWActive.value.not()
        val response = apiService.getRandomAnime(isNSFWActive)
        emit(response)
    }.flowOn(Dispatchers.IO)
}