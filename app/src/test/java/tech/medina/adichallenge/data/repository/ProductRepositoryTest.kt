package tech.medina.adichallenge.data.repository

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.utils.FakeApi

@ExperimentalCoroutinesApi
class ProductRepositoryTest : BaseTest() {


    private val api = mockk<ProductApi>() {
        coEvery { getAllProducts() } returns FakeApi.productList
        coEvery { getProductById(any())} returns FakeApi.product
    }

    @Test
    fun `get all products successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeApi.productList
        val repository = ProductRepository(api)
        val products = repository.getAllProducts()
        coVerify {
            api.getAllProducts()
        }
        with(products) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isNotEmpty()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `get product by id successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeApi.product
        val id = "1"
        val repository = ProductRepository(api)
        val product = repository.getProductById(id)
        coVerify {
            api.getProductById(id)
        }
        with(product) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }
}