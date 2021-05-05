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
import tech.medina.adichallenge.domain.models.Review
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
    }

    val products = savedState.getLiveData<DataState<List<Product>>>(KEY_REVIEW_RESULT, DataState.Loading)

    fun addProductReview(productId: String, review: Review) {
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                addProductReviewUseCase(productId, review)
            }
            savedState.set(KEY_REVIEW_RESULT, result)
        }
    }

}