package tech.medina.adichallenge.ui.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.domain.models.Review
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.ProductViewModel
import tech.medina.adichallenge.ui.utils.visible


/**
 * A fragment representing a product detail screen.
 */

class ProductDetailFragment : BaseFragment() {

    companion object {
        private const val KEY_PRODUCT_ID = "product.id"

        fun create(productId: String): ProductDetailFragment = ProductDetailFragment().apply {
            this.productId = productId
        }
    }

    override val viewModel: ProductViewModel by viewModels()
    lateinit var binding: FragmentItemDetailBinding
    private var productId: String? = null

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (productId == null) {
            onGetProductError(requireContext().getString(R.string.product_detail_message_error), shouldClose = true)
        } else {
            viewModel.getProductById(productId!!)
        }
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        productId?.let {
            viewModel.getReviewsForProductWithId(it)
        }
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

    private fun initObservers() {
        viewModel.productDetail.observe(this, Observer {
            it?.let {  product ->
                setupToolbar(product)
                onGetProductSuccess(product)
            }
        })
        viewModel.loader.observe(this, Observer {
            it?.let { show ->
                showLoader(show)
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let { error ->
                onGetProductError(error)
            }
        })
        viewModel.productReviews.observe(this, Observer {
            it?.let { reviews ->
                if (reviews.isEmpty()) {
                    onGetReviewsError(requireContext().getString(R.string.product_detail_reviews_message_empty), isEmpty = true)
                } else {
                    onGetReviewsSuccess(reviews)
                }
            }
        })
        viewModel.reviewsError.observe(this, Observer {
            it?.let { error ->
                onGetReviewsError(error)
            }
        })
        viewModel.reviewsLoader.observe(this, Observer {
            it?.let { show ->
                showReviewsLoader(show)
            }
        })
    }

    private fun setupToolbar(data: Product) {
        binding.toolbar.apply {
            title = data.name
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener { baseActivity.finish() }
        }
    }

    //PRODUCT METHODS

    private fun onGetProductSuccess(data: Product) {
        binding.content.root.visible()
        binding.collapsingToolbar.visible()
        binding.message.root.visible(false)
        imageLoader.loadWithUrl(data.imageUrl, binding.header.image)
        binding.header.name.text = data.name
        binding.header.price.text = data.getFormattedPrice(requireContext())
        binding.content.description.text = data.description
    }

    private fun onGetProductError(error: Any?, shouldClose: Boolean = false) {
        binding.content.root.visible(false)
        binding.collapsingToolbar.visible(false)
        binding.message.root.visible()
        binding.message.text.text = error.toString()
        if (shouldClose) {
            binding.message.button.text = getString(R.string.button_close)
            binding.message.button.setOnClickListener(::onCloseButtonClick)
        } else {
            binding.message.button.setOnClickListener(::onRetryButtonClick)
        }
    }

    private fun showLoader(show: Boolean) {
        binding.progress.visible(show)
    }

    //REVIEW METHODS

    private fun onGetReviewsSuccess(list: List<Review>) {
        val ratingAverage = list.map { it.rating }.average()
        binding.content.reviews.apply {
            reviewMessage.root.visible(false)
            texts.visible()
            average.text = getString(R.string.product_review_rating_text, ratingAverage)
            stars.progress = ratingAverage.toInt()
            totalReviews.text = getString(R.string.product_detail_reviews_total, list.size)
            buttonViewAll.setOnClickListener(::onViewAllReviewsButtonClick)
            buttonAddReview.setOnClickListener(::onAddReviewButtonClick)
        }

    }

    private fun onGetReviewsError(error: Any?, isEmpty: Boolean = false) {
        binding.content.reviews.apply {
            texts.visible(false)
            reviewMessage.root.visible()
            reviewMessage.text.text = error.toString()
            if (isEmpty) {
                reviewMessage.button.text = getString(R.string.product_detail_reviews_button_add)
                reviewMessage.button.setOnClickListener(::onAddReviewButtonClick)
            } else {
                reviewMessage.button.setOnClickListener(::onRetryReviewsButtonClick)
            }
        }
    }

    private fun showReviewsLoader(show: Boolean) {
        binding.content.reviews.progress.visible(show)
    }

    //CLICK METHODS

    private fun onRetryButtonClick(view: View) {
        productId?.let {
            viewModel.getProductById(it)
        }
    }

    private fun onRetryReviewsButtonClick(view: View) {
        productId?.let {
            viewModel.getReviewsForProductWithId(it)
        }
    }

    private fun onCloseButtonClick(view: View) {
        baseActivity.finish()
    }

    private fun onAddReviewButtonClick(view: View) {
        productId?.let {
            navigator.goToAddReview(baseActivity, it)
        }
    }

    private fun onViewAllReviewsButtonClick(view: View) {
        productId?.let {
            navigator.goToViewReviews(baseActivity, it)
        }
    }

}