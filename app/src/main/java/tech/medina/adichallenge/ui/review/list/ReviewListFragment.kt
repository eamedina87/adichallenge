package tech.medina.adichallenge.ui.review.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.databinding.FragmentReviewListBinding
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.detail.ProductDetailFragment
import tech.medina.adichallenge.ui.review.ReviewViewModel
import tech.medina.adichallenge.ui.review.detail.ReviewDetailFragment
import tech.medina.adichallenge.ui.review.list.adapter.ReviewListAdapter
import tech.medina.adichallenge.ui.utils.Constants
import tech.medina.adichallenge.ui.utils.visible

class ReviewListFragment : BaseFragment() {

    companion object {
        private const val KEY_PRODUCT_ID = "product.id"

        fun create(productId: String): ReviewListFragment = ReviewListFragment().apply {
            this.productId = productId
        }
    }

    private var productId: String? = null
    override val viewModel: ReviewViewModel by viewModels()
    lateinit var binding: FragmentReviewListBinding

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentReviewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        setupToolbar()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        productId?.let {
            viewModel.getReviewsForProductWithId(it)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener { baseActivity.finish() }
        }
    }

    private fun initObservers() {
        viewModel.productReviews.observe(this, Observer {
            it?.let { reviews ->
                if (reviews.isEmpty()) {
                    onGetReviewsError(requireContext().getString(R.string.product_detail_reviews_message_empty), isEmpty = true)
                } else {
                    onGetReviewsSuccess(reviews)
                }
            }
        })
        viewModel.reviewsLoader.observe(this, Observer {
            it?.let { show ->
                showReviewsLoader(show)
            }
        })
        viewModel.reviewsError.observe(this, Observer {
            it?.let { error ->
                onGetReviewsError(error)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        productId?.let {
            outState.putString(KEY_PRODUCT_ID, it)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getString(KEY_PRODUCT_ID)?.let {
            productId = it
        }
    }

    private fun onGetReviewsSuccess(list: List<Review>) {
        binding.reviewMessage.root.visible(false)
        binding.recyclerview.adapter = ReviewListAdapter().apply {
            submitList(list)
        }
    }

    private fun onGetReviewsError(error: Any?, isEmpty: Boolean = false) {
        binding.recyclerview.visible(false)
        binding.reviewMessage.apply {
            root.visible()
            text.text = error.toString()
            if (isEmpty) {
                button.text = getString(R.string.product_detail_reviews_button_add)
                button.setOnClickListener(::onAddReviewButtonClick)
            } else {
                button.setOnClickListener(::onRetryReviewsButtonClick)
            }
        }
    }

    private fun showReviewsLoader(show: Boolean) {
        binding.progress.visible(show)
    }

    private fun onAddReviewButtonClick(view: View) {
        productId?.let {
            navigator.goToAddReview(baseActivity, it)
        }
    }

    private fun onRetryReviewsButtonClick(view: View) {
        productId?.let {
            viewModel.getReviewsForProductWithId(it)
        }
    }

}