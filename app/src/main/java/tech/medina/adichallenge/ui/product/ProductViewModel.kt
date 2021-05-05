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
import tech.medina.adichallenge.domain.usecase.IGetAllProductsUseCase
import tech.medina.adichallenge.domain.usecase.IGetProductByIdUseCase
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher,
    private val getAllProductsUseCase: IGetAllProductsUseCase,
    private val getProductByIdUseCase: IGetProductByIdUseCase,
): ViewModel() {

    companion object {
        const val KEY_PRODUCT_LIST = "product.list"
        const val KEY_PRODUCT_DETAIL = "product.detail"
    }

    val products = savedState.getLiveData<DataState<List<Product>>>(KEY_PRODUCT_LIST, DataState.Loading)
    val productDetail = savedState.getLiveData<DataState<Product>>(KEY_PRODUCT_DETAIL, DataState.Loading)

    fun getAllProducts() {
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                getAllProductsUseCase()
            }
            savedState.set(KEY_PRODUCT_LIST, result)
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                getProductByIdUseCase(id)
            }
            savedState.set(KEY_PRODUCT_DETAIL, result)
        }
    }

}