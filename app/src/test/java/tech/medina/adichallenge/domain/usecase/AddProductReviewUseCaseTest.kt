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
import tech.medina.adichallenge.data.api.dto.ReviewDto
import tech.medina.adichallenge.data.mapper.IMapper
import tech.medina.adichallenge.data.repository.IReviewRepository
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.utils.FakeApi
import tech.medina.adichallenge.utils.FakeModels

@ExperimentalCoroutinesApi
class AddProductReviewUseCaseTest : BaseTest() {

    private val repository = mockk<IReviewRepository>() {
        coEvery { addReviewForProductWithId(any(), any()) } returns FakeApi.review
    }

    private val reviewMapper = mockk<IMapper<Review, ReviewDto>> {
        every { map(any()) } returns FakeApi.review
    }

    @Test
    fun `post review successfully`() = dispatcher.runBlockingTest {
        val id = "ABC01"
        val useCase = AddProductReviewUseCase(repository, reviewMapper)
        val result = useCase(id, 3, "the message")
        coVerify {
            reviewMapper.map(any())
            repository.addReviewForProductWithId(id, any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Success)
            this as DataState.Success
            Truth.assertThat(this.result).isEqualTo(true)
        }
    }

    @Test
    fun `post review with exception`() = dispatcher.runBlockingTest {
        coEvery { repository.getReviewsForProductWithId(any()) } throws Exception("An error occurred")
        val id = "ABC01"
        val useCase = AddProductReviewUseCase(repository, reviewMapper)
        val result = useCase(id, 3, "the message")
        coVerify {
            reviewMapper.map(any())
            repository.addReviewForProductWithId(id, any())
        }
        with (result) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this is DataState.Error)
        }
    }
}