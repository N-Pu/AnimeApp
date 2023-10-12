package com.project.toko.core.repository


import android.util.Log
import com.project.toko.characterDetailedScreen.model.characterFullModel.CharacterFullModel
import com.project.toko.characterDetailedScreen.model.characterPicture.CharacterPicturesModel
import com.project.toko.detailScreen.model.detailModel.old.AnimeDetailModel
import com.project.toko.homeScreen.model.castModel.CastModel
import com.project.toko.homeScreen.model.newAnimeSearchModel.NewAnimeSearchModel
import com.project.toko.homeScreen.model.staffModel.StaffModel
import com.project.toko.staffDetailedScreen.model.personFullModel.PersonFullModel
import com.project.toko.producerDetailedScreen.model.producerModel.ProducerFullModel
import kotlinx.coroutines.delay
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.SocketTimeoutException



interface MalApiService {
    companion object{
        const val BASE_URL = "https://api.jikan.moe/"
    }

    @GET("${BASE_URL}v4/anime?")
    suspend fun getAnimeSearchByName(
        @Query("sfw") sfw: Boolean,
        @Query("page") page: Int = 1,
        @Query("q") query: String? = null,
        @Query("type") type: String? = null,
        @Query("genres") genres: String? = null,
        @Query("min_score") min_score: String? = null,
        @Query("max_score") max_score: String? = null,
        @Query("rating") rating: String? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("sort") sort: String? = null,
    ): Response<NewAnimeSearchModel>

    @GET("${BASE_URL}v4/anime/{id}/full")
    suspend fun getDetailsFromAnime(@Path("id") id: Int): Response<AnimeDetailModel>

    @GET("${BASE_URL}v4/random/anime")
    suspend fun getRandomAnime(): Response<AnimeDetailModel>

    @GET("${BASE_URL}v4/anime/{id}/characters")
    suspend fun getCharactersFromId(@Path("id") id: Int): Response<CastModel>
    {
        try {
            return getCharactersFromId(id)
        } catch (e: HttpException) {
            if (e.code() == 404) {
                return Response.success(null)
            }
            throw e
        }
    }

    @GET("${BASE_URL}v4/characters/{id}/full")
    suspend fun getCharacterFullFromId(@Path("id") id: Int): Response<CharacterFullModel>

    @GET("${BASE_URL}v4/characters/{id}/pictures")
    suspend fun getCharacterFullPictures(@Path("id") id: Int): Response<CharacterPicturesModel>

    @GET("${BASE_URL}v4/anime/{id}/staff")
    suspend fun getStaffFromId(@Path("id") id: Int): Response<StaffModel> {
        try {
            return getStaffFromId(id)
        } catch (e: HttpException) {
            if (e.code() == 404) {
                return Response.success(null)
            }
            throw e
        }
    }

    @GET("${BASE_URL}v4/people/{id}/full")
    suspend fun getPersonFullFromId(@Path("id") id: Int): Response<PersonFullModel> {
        var retryCount = 0
        while (retryCount < 3) { // повторяем запрос не более 3 раз
            try {
                return getPersonFullFromId(id)
            } catch (e: Exception) {
                // обрабатываем ошибки
                when (e) {
                    is SocketTimeoutException, is HttpException -> {
                        retryCount++
                        Log.e("MalApiService", "Error occurred: ${e.message}")
                        delay(1000) // задержка на 1 секунд перед повторным запросом
                    }

                    else -> throw e // выбрасываем ошибку, которую не умеем обрабатывать
                }
            }
        }
        throw Exception("Failed to get response after $retryCount retries")
    }


    @GET("${BASE_URL}v4/producers/{id}/full")
    suspend fun getProducerFullFromId(@Path("id") id: Int): Response<ProducerFullModel> {
        var retryCount = 0
        while (retryCount < 3) { // повторяем запрос не более 3 раз
            try {
                return getProducerFullFromId(id)
            } catch (e: Exception) {
                // обрабатываем ошибки
                when (e) {
                    is SocketTimeoutException, is HttpException -> {
                        retryCount++
                        Log.e("MalApiService", "Error occurred: ${e.message}")
                        delay(1000) // задержка на 1 секунд перед повторным запросом
                    }

                    else -> throw e // выбрасываем ошибку, которую не умеем обрабатывать
                }
            }
        }
        throw Exception("Failed to get response after $retryCount retries")
    }
}
