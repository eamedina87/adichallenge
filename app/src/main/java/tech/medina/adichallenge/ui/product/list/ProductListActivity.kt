package tech.medina.adichallenge.ui.product.list

import android.os.Bundle
import android.view.View
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.ActivityDefaultBinding
import tech.medina.adichallenge.ui.product.detail.ProductDetailActivity
import tech.medina.adichallenge.ui.common.BaseActivity

/**
 * An activity representing a list of Products.
 */

class ProductListActivity : BaseActivity() {

    private lateinit var binding: ActivityDefaultBinding

    override fun getBindingRoot(): View {
        binding = ActivityDefaultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.frameLayout, ProductListFragment.create(), "list")
        }
    }

}