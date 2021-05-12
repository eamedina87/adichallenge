package tech.medina.adichallenge.ui.common

import android.content.Intent
import android.os.Bundle
import tech.medina.adichallenge.ui.product.detail.ProductDetailActivity
import tech.medina.adichallenge.ui.review.detail.ReviewDetailActivity
import tech.medina.adichallenge.ui.review.list.ReviewListActivity
import tech.medina.adichallenge.ui.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private fun goToActivity(source: BaseActivity,
                             destination: Class<*>,
                             extras: Bundle? = null,
                             requestCode: Int? = null,
                             finish: Boolean = false) {

        val intent = Intent(source, destination)

        extras?.let {
            intent.putExtras(extras)
        }

        if (requestCode == null) {
            source.startActivity(intent)
        } else {
            source.startActivityForResult(intent, requestCode)
        }

        if (finish) source.finish()

    }

    fun goToDetail(source: BaseActivity, id: String) {
        val extras = Bundle().apply {
            putString(Constants.INTENT_EXTRA_PRODUCT_ID, id)
        }
        goToActivity(source = source, destination = ProductDetailActivity::class.java, extras = extras)
    }

    fun goToAddReview(source: BaseActivity, id: String) {
        val extras = Bundle().apply {
            putString(Constants.INTENT_EXTRA_PRODUCT_ID, id)
        }
        goToActivity(source = source, destination = ReviewDetailActivity::class.java, extras = extras)
    }

    fun goToViewReviews(source: BaseActivity, id: String) {
        val extras = Bundle().apply {
            putString(Constants.INTENT_EXTRA_PRODUCT_ID, id)
        }
        goToActivity(source = source, destination = ReviewListActivity::class.java, extras = extras)
    }

}