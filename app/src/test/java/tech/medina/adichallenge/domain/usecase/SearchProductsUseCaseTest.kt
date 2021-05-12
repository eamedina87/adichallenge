package tech.medina.adichallenge.domain.usecase

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.utils.FakeEntity
import tech.medina.adichallenge.utils.FakeModels

class SearchProductsUseCaseTest : BaseTest() {

    private val repository = mockk<IProductRepository> {
        coEvery { searchProducts(any()) } returns FakeEntity.productList()
    }
    private val reviewMapper = mockk<IMapper<ProductEntity, Product>> {
        every { map(any()) } returns FakeModels.product
    }

    @Test
    fun `search query successfully`() = dispatcher.runBlockingTest {
        val query = "ABC01"
        val expectedResult = FakeModels.productList()
        val useCase = SearchProductsUseCase(repository, reviewMapper)
        val result = useCase(query)
        coVerify {
            repository.searchProducts(query)
            reviewMapper.map(any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Success)
            this as DataState.Success
            Truth.assertThat(this.result).isEqualTo(expectedResult)
        }
    }

    @Test
    fun `get all reviews with exception`() = dispatcher.runBlockingTest {
        coEvery { repository.searchProducts(any()) } throws Exception("An error occurred")
        val query = "ABC01"
        val useCase = SearchProductsUseCase(repository, reviewMapper)
        val result = useCase(query)
        coVerify {
            repository.searchProducts(query)
        }
        coVerify (exactly = 0) {
            reviewMapper.map(any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Error)
        }
    }

}