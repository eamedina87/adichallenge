package tech.medina.adichallenge.ui.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.ProductViewModel


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ProductDetailActivity]
 * on handsets.
 */
class ProductDetailFragment : BaseFragment() {

    override val viewModel: ProductViewModel by viewModels()
    lateinit var binding: FragmentItemDetailBinding

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

}