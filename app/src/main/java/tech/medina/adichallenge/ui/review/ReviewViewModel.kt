package tech.medina.adichallenge.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.usecase.IAddProductReviewUseCase
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher,
    private val addProductReviewUseCase: IAddProductReviewUseCase
): ViewModel() {

    companion object {
        const val KEY_REVIEW_RESULT = "review.result"
        const val KEY_REVIEW_LOADER = "review.loading"
        const val KEY_REVIEW_ERROR = "review.error"
    }

    val addReviewResult = savedState.getLiveData<Boolean>(KEY_REVIEW_RESULT)
    val loader = savedState.getLiveData<Boolean>(KEY_REVIEW_LOADER)
    val error = savedState.getLiveData<String>(KEY_REVIEW_ERROR)

    fun addProductReview(productId: String, rating: Int, message: String) {
        savedState.set(KEY_REVIEW_LOADER, true)
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                addProductReviewUseCase.invoke(productId, rating, message)
            }
            savedState.set(KEY_REVIEW_LOADER, false)
            when (result) {
                is DataState.Success -> savedState.set(KEY_REVIEW_RESULT, result.result)
                is DataState.Error -> savedState.set(KEY_REVIEW_ERROR, result.error.toString())
                is DataState.Loading -> savedState.set(KEY_REVIEW_LOADER, true)
            }
        }
    }

}