package tech.medina.adichallenge.ui.review.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.FragmentReviewDetailBinding
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.review.ReviewViewModel
import tech.medina.adichallenge.ui.utils.visible

class ReviewDetailFragment : BaseFragment() {

    companion object {
        private const val KEY_MESSAGE = "review.message"
        fun create(productId: String): ReviewDetailFragment = ReviewDetailFragment().apply {
            this.productId = productId
        }
    }

    private var productId: String? = null
    override val viewModel: ReviewViewModel by viewModels()
    lateinit var binding: FragmentReviewDetailBinding

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentReviewDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.addReviewResult.observe(this, Observer {
            it?.let {
                onAddReviewResult(it)
            }
        })
        viewModel.loader.observe(this, Observer {
            it?.let {
                showLoader(it)
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let {
                binding.error.text = it
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_MESSAGE, binding.message.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getString(KEY_MESSAGE)?.let {
            binding.message.append(it)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener { baseActivity.finish() }
        }
    }

    //LISTENERS

    private fun setupListeners() {
        binding.stars.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) binding.rating.text = rating.toInt().toString()
        }
        binding.buttonAddReview.setOnClickListener(::onAddReviewClicked)
    }

    //RESULTS

    private fun onAddReviewResult(result: Boolean) {
        if (result) {
            showMessage(getString(R.string.product_review_message_success))
            baseActivity.finish()
        } else {
            binding.error.text = getString(R.string.product_review_message_error)
        }
    }

    //CLICK METHODS

    private fun onAddReviewClicked(view: View) {
        if (productId == null) return
        val rating = binding.stars.progress
        if (rating == 0) {
            showMessage(getString(R.string.product_review_message_rating))
            return
        }
        val message = binding.message.text.toString()
        if (message.isBlank()) {
            showMessage(getString(R.string.product_review_message_text))
            return
        }
        viewModel.addProductReview(productId!!, rating, message)
    }

    private fun showLoader(show: Boolean) {
        binding.progress.visible(show)
    }

}