package tech.medina.adichallenge.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import tech.medina.adichallenge.databinding.ActivityItemListBinding
import tech.medina.adichallenge.ui.base.BaseActivity
import tech.medina.adichallenge.ui.list.ItemListActivity

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : BaseActivity() {

    override val viewModel: ViewModel? = null
    private lateinit var binding: ActivityItemListBinding

    override fun getBindingRoot(): View {
        binding = ActivityItemListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}