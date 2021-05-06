package tech.medina.adichallenge.ui.product.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import tech.medina.adichallenge.databinding.ActivityItemListBinding
import tech.medina.adichallenge.ui.common.BaseActivity
import tech.medina.adichallenge.ui.product.list.ProductListActivity

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ProductListActivity].
 */
class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityItemListBinding

    override fun getBindingRoot(): View {
        binding = ActivityItemListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}