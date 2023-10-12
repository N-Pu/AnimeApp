package com.project.toko.producerDetailedScreen.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.toko.core.repository.MalApiService
import com.project.toko.producerDetailedScreen.model.producerModel.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProducerFullViewModel @Inject constructor(private val malApiService: MalApiService) :
    ViewModel() {
    private val _producerFull = MutableStateFlow<Data?>(null)
    private val producerCache = mutableMapOf<Int, Data?>()
    val producerFull = _producerFull.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _loadedId = mutableStateOf(0)
    val loadedId = _loadedId
    fun getProducerFromId(mal_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isSearching.value = true
                val response = malApiService.getProducerFullFromId(mal_id)
                if (response.isSuccessful) {
                    val producer = response.body()?.data
                    producerCache[mal_id] = producer
                    _loadedId.value = mal_id
                    _producerFull.value = producer
                }
            } catch (e: Exception) {
                Log.e("StaffFullByIdViewModel", e.message.toString())
            } finally {
                _isSearching.value = false
            }
        }
    }

}