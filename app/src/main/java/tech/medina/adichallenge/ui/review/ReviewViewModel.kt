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
import tech.medina.adichallenge.domain.usecase.IGetProductReviewsUseCase
import tech.medina.adichallenge.ui.product.ProductViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher,
    private val addProductReviewUseCase: IAddProductReviewUseCase,
    private val getProductReviewsUseCase: IGetProductReviewsUseCase
): ViewModel() {

    companion object {
        private const val KEY_REVIEW_RESULT = "review.result"
        private const val KEY_REVIEW_LOADER = "review.loading"
        private const val KEY_REVIEW_ERROR = "review.error"
        private const val KEY_PRODUCT_REVIEWS_LIST = "product.reviews.list"
        private const val KEY_PRODUCT_REVIEWS_LOADER = "product.reviews.loader"
        private const val KEY_PRODUCT_REVIEWS_ERROR = "product.reviews.error"
    }

    val addReviewResult = savedState.getLiveData<Boolean>(KEY_REVIEW_RESULT)
    val loader = savedState.getLiveData<Boolean>(KEY_REVIEW_LOADER)
    val error = savedState.getLiveData<String>(KEY_REVIEW_ERROR)
    val productReviews = savedState.getLiveData<List<Review>>(KEY_PRODUCT_REVIEWS_LIST)
    val reviewsLoader = savedState.getLiveData<Boolean>(KEY_PRODUCT_REVIEWS_LOADER)
    val reviewsError = savedState.getLiveData<Any?>(KEY_PRODUCT_REVIEWS_ERROR)

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

    fun getReviewsForProductWithId(id: String) {
        viewModelScope.launch {
            savedState.set(KEY_PRODUCT_REVIEWS_LOADER, true)
            val result = withContext(dispatcher) { getProductReviewsUseCase(id) }
            savedState.set(KEY_PRODUCT_REVIEWS_LOADER, false)
            when (result) {
                is DataState.Success -> savedState.set(KEY_PRODUCT_REVIEWS_LIST, result.result)
                is DataState.Error -> savedState.set(KEY_PRODUCT_REVIEWS_ERROR, result.error)
                is DataState.Loading -> savedState.set(KEY_PRODUCT_REVIEWS_LOADER, true)
            }
        }
    }

}