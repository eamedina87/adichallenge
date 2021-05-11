package tech.medina.adichallenge.ui.product

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
import tech.medina.adichallenge.domain.usecase.IGetAllProductsUseCase
import tech.medina.adichallenge.domain.usecase.IGetProductByIdUseCase
import tech.medina.adichallenge.domain.models.SORT
import tech.medina.adichallenge.domain.usecase.GetProductReviewsUseCase
import tech.medina.adichallenge.domain.usecase.IGetProductReviewsUseCase
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher,
    private val getAllProductsUseCase: IGetAllProductsUseCase,
    private val getProductByIdUseCase: IGetProductByIdUseCase,
    private val getProductReviewsUseCase: IGetProductReviewsUseCase
): ViewModel() {

    companion object {
        private const val KEY_PRODUCT_LIST = "product.list"
        private const val KEY_PRODUCT_DETAIL = "product.detail"
        private const val KEY_PRODUCT_REVIEWS_LIST = "product.reviews.list"
        private const val KEY_PRODUCT_REVIEWS_LOADER = "product.reviews.loader"
        private const val KEY_PRODUCT_REVIEWS_ERROR = "product.reviews.error"
        private const val KEY_PRODUCT_ERROR = "product.error"
        private const val KEY_PRODUCT_LOADER = "product.loading"
    }

    val error = savedState.getLiveData<Any?>(KEY_PRODUCT_ERROR)
    val loader = savedState.getLiveData<Boolean>(KEY_PRODUCT_LOADER)
    val products = savedState.getLiveData<List<Product>>(KEY_PRODUCT_LIST)
    val productDetail = savedState.getLiveData<Product>(KEY_PRODUCT_DETAIL)
    val productReviews = savedState.getLiveData<List<Review>>(KEY_PRODUCT_REVIEWS_LIST)
    val reviewsLoader = savedState.getLiveData<Boolean>(KEY_PRODUCT_REVIEWS_LOADER)
    val reviewsError = savedState.getLiveData<Any?>(KEY_PRODUCT_REVIEWS_ERROR)

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            savedState.set(KEY_PRODUCT_LOADER, true)
            val result = withContext(dispatcher) { getAllProductsUseCase() }
            savedState.set(KEY_PRODUCT_LOADER, false)
            when (result) {
                is DataState.Success -> savedState.set(KEY_PRODUCT_LIST, result.result)
                is DataState.Error -> savedState.set(KEY_PRODUCT_ERROR, result.error)
                is DataState.Loading -> savedState.set(KEY_PRODUCT_LOADER, true)
            }
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            savedState.set(KEY_PRODUCT_LOADER, true)
            val result = withContext(dispatcher) { getProductByIdUseCase(id) }
            savedState.set(KEY_PRODUCT_LOADER, false)
            when (result) {
                is DataState.Success -> savedState.set(KEY_PRODUCT_DETAIL, result.result)
                is DataState.Error -> savedState.set(KEY_PRODUCT_ERROR, result.error)
                is DataState.Loading -> savedState.set(KEY_PRODUCT_LOADER, true)
            }
        }
    }

    fun sortProductsBy(sort: SORT) {
        val products = savedState.get<List<Product>>(KEY_PRODUCT_LIST)
        products?.let {  list ->
            val sortedList = when (sort) {
                SORT.DEFAULT -> list
                SORT.AZ -> list.sortedBy { it.name }
                SORT.ZA -> list.sortedByDescending { it.name }
                SORT.PRICE_LOW_HIGH -> list.sortedBy { it.price }
                SORT.PRICE_HIGH_LOW -> list.sortedByDescending { it.price }
            }
            savedState.set(KEY_PRODUCT_LIST, sortedList)
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