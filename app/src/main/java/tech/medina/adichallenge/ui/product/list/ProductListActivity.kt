package tech.medina.adichallenge.ui.product.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import tech.medina.adichallenge.ui.product.detail.ProductDetailActivity
import tech.medina.adichallenge.databinding.ActivityItemListBinding
import tech.medina.adichallenge.ui.common.BaseActivity

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ProductDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ProductListActivity : BaseActivity() {

    private lateinit var binding: ActivityItemListBinding

    override fun getBindingRoot(): View {
        binding = ActivityItemListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

}