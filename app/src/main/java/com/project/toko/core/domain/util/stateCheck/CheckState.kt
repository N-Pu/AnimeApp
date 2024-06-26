package com.project.toko.core.domain.util.stateCheck

sealed class CheckState<out T> {
    data class Success<T>(val data: T) : CheckState<T>()
    data class Error(val message: String) : CheckState<Nothing>()
    data object Loading : CheckState<Nothing>()
}