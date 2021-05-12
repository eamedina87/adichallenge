package tech.medina.adichallenge.data.repository

import com.google.common.truth.Truth
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.ProductApi
import tech.medina.adichallenge.data.api.dto.ProductDto
import tech.medina.adichallenge.data.db.AdiChallengeDatabase
import tech.medina.adichallenge.data.db.entity.ProductEntity
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.utils.FakeApi
import tech.medina.adichallenge.utils.FakeEntity

@ExperimentalCoroutinesApi
class ProductRepositoryTest : BaseTest() {


    private val api = mockk<ProductApi> {
        coEvery { getAllProducts() } returns FakeApi.productList()
        coEvery { getProductById(any())} returns FakeApi.product
    }

    private val database = mockk<AdiChallengeDatabase> {
        coEvery { productDao().getAll() } returns FakeEntity.productList
        coEvery { productDao().insert(any()) } just Runs
        coEvery { productDao().search(any()) } returns FakeEntity.productList
    }

    private val mapper = mockk<IMapper<ProductDto, ProductEntity>> {
        every { map(any()) } returns FakeEntity.product
    }

    @Test
    fun `get all products successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeEntity.productList
        val repository = ProductRepository(api, database, mapper)
        val products = repository.getAllProducts()
        coVerifySequence {
            api.getAllProducts()
            database.productDao().insert(any())
            database.productDao().getAll()
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
        val repository = ProductRepository(api, database, mapper)
        val product = repository.getProductById(id)
        coVerify {
            api.getProductById(id)
        }
        with(product) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `search product with valid query successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeEntity.productList
        val repository = ProductRepository(api, database, mapper)
        val product = repository.searchProducts("abc")
        coVerify {
            database.productDao().search(any())
        }
        with(product) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `search product with empty query successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeEntity.productList
        val repository = ProductRepository(api, database, mapper)
        val product = repository.searchProducts("")
        coVerify {
            database.productDao().getAll()
        }
        with(product) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }
}