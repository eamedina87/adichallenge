package tech.medina.adichallenge.domain.usecase

import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.mapper.ProductMapper
import tech.medina.adichallenge.data.mapper.ReviewMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.utils.FakeApi
import tech.medina.adichallenge.utils.FakeEntity
import tech.medina.adichallenge.utils.FakeModels
import tech.medina.adichallenge.utils.FakeRepo

@ExperimentalCoroutinesApi
class GetAllProductsUseCaseTest : BaseTest() {

    private val repository = mockk<IProductRepository>() {
        coEvery { getAllProducts() } returns FakeEntity.productList
        coEvery { getProductById(any()) } returns FakeApi.product
    }
    private val productMapper = mockk<IMapper<ProductEntity, Product>> {
        every { map(any()) } returns FakeModels.product
    }

    @Test
    fun `get all products successfully`() = dispatcher.runBlockingTest {
        val useCase = GetAllProductsUseCase(repository, productMapper)
        val result = useCase()
        coVerify {
            repository.getAllProducts()
            //productMapper.map(any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Success)
        }
    }

    @Test
    fun `get all products with exception`() = dispatcher.runBlockingTest {
        coEvery { repository.getAllProducts() } throws Exception("An error occurred")
        val useCase = GetAllProductsUseCase(repository, productMapper)
        val result = useCase()
        coVerify {
            repository.getAllProducts()
        }
        coVerify (exactly = 0) {
            productMapper.map(any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Error)
        }
    }

}