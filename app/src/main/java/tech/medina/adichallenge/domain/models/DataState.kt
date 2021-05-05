package tech.medina.adichallenge.domain.models

sealed class DataState<out T> {

    object Loading: DataState<Nothing>()
    class Success<out T>(val result: T): DataState<T>()
    class Error(val error: Any?): DataState<Nothing>()

}