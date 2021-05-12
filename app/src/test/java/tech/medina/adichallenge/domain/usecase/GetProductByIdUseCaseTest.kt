package tech.medina.adichallenge.domain.usecase

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IProductRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.utils.FakeApi
import tech.medina.adichallenge.utils.FakeModels

@ExperimentalCoroutinesApi
class GetProductByIdUseCaseTest : BaseTest() {

    private val repository = mockk<IProductRepository>() {
        coEvery { getProductById(any()) } returns FakeApi.product
    }
    private val productMapper = mockk<IMapper<ProductDto, Product>>() {
        every { map(any()) } returns FakeModels.product
    }

    @Test
    fun `get product by id successfully`() = dispatcher.runBlockingTest {
        val useCase = GetProductByIdUseCase(repository, productMapper)
        val id = "id"
        val result = useCase(id)
        coVerify (exactly = 1) {
            repository.getProductById(id)
            productMapper.map(any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Success)
        }
    }

    @Test
    fun `get product by id with exception`() = dispatcher.runBlockingTest {
        coEvery { repository.getProductById(any()) } throws Exception("An error occurred")
        val useCase = GetProductByIdUseCase(repository, productMapper)
        val id = "id"
        val result = useCase(id)
        coVerify {
            repository.getProductById(id)
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