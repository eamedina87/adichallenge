package tech.medina.adichallenge.ui.product.detail

import android.os.Bundle
import android.view.View
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.ActivityDefaultBinding
import tech.medina.adichallenge.ui.common.BaseActivity
import tech.medina.adichallenge.ui.product.list.ProductListActivity
import tech.medina.adichallenge.ui.utils.Constants

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ProductListActivity].
 */
class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDefaultBinding

    override fun getBindingRoot(): View {
        binding = ActivityDefaultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val productId = intent?.extras?.getString(Constants.INTENT_EXTRA_PRODUCT_ID, "") ?: ""
            addFragment(R.id.frameLayout, ProductDetailFragment.create(productId), "detail")
        }
    }


}