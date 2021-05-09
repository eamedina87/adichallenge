package tech.medina.adichallenge.data.repository

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import tech.medina.adichallenge.base.BaseTest
import tech.medina.adichallenge.data.api.ReviewApi
import tech.medina.adichallenge.utils.FakeApi

@ExperimentalCoroutinesApi
class ReviewRepositoryTest : BaseTest() {

    private val api = mockk<ReviewApi> {
        coEvery { getAllReviewsForProductWithId(any()) } returns FakeApi.reviewList
        coEvery { postReviewForProductWithId(any(), any())} returns FakeApi.review
    }

    @Test
    fun `get all reviews successfully`() = dispatcher.runBlockingTest {
        val expectedValue = FakeApi.reviewList
        val productId = "ABCD01"
        val repository = ReviewRepository(api)
        val reviews = repository.getReviewsForProductWithId(productId)
        coVerify {
            api.getAllReviewsForProductWithId(productId)
        }
        with(reviews) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isNotEmpty()
            Truth.assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun `post product review successfully`() = dispatcher.runBlockingTest {
        val id = "ABC01"
        val review = FakeApi.review
        val repository = ReviewRepository(api)
        val sentReview = repository.addReviewForProductWithId(id, review)
        coVerify {
            api.postReviewForProductWithId(id, review)
        }
        with(sentReview) {
            Truth.assertThat(this).isNotNull()
            Truth.assertThat(this).isEqualTo(review)
        }
    }

}