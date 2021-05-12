package tech.medina.adichallenge.ui.review.detail

import android.os.Bundle
import android.view.View
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.ActivityDefaultBinding
import tech.medina.adichallenge.ui.common.BaseActivity
import tech.medina.adichallenge.ui.utils.Constants

/**
 * An activity representing a review detail.
 * Used for adding a new review.
 */

class ReviewDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDefaultBinding

    override fun getBindingRoot(): View {
        binding = ActivityDefaultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val productId = intent?.extras?.getString(Constants.INTENT_EXTRA_PRODUCT_ID, "") ?: ""
            addFragment(R.id.frameLayout, ReviewDetailFragment.create(productId), "review_detail")
        }
    }

}