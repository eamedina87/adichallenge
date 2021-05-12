package tech.medina.adichallenge.domain.usecase

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IReviewRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.utils.FakeApi
import tech.medina.adichallenge.utils.FakeModels

@ExperimentalCoroutinesApi
class GetProductReviewsUseCaseTest : BaseTest() {

    private val repository = mockk<IReviewRepository>() {
        coEvery { getReviewsForProductWithId(any()) } returns FakeApi.reviewList()
    }
    private val reviewMapper = mockk<IMapper<ReviewDto, Review>> {
        every { map(any()) } returns FakeModels.review
    }

    @Test
    fun `get all reviews successfully`() = dispatcher.runBlockingTest {
        val id = "ABC01"
        val expectedResult = FakeModels.reviewList()
        val useCase = GetProductReviewsUseCase(repository, reviewMapper)
        val result = useCase(id)
        coVerify {
            repository.getReviewsForProductWithId(id)
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
        coEvery { repository.getReviewsForProductWithId(any()) } throws Exception("An error occurred")
        val id = "ABC01"
        val useCase = GetProductReviewsUseCase(repository, reviewMapper)
        val result = useCase(id)
        coVerify {
            repository.getReviewsForProductWithId(id)
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