package tech.medina.adichallenge.ui.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import tech.medina.adichallenge.R
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.ProductViewModel
import tech.medina.adichallenge.ui.utils.visible


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ProductDetailActivity]
 * on handsets.
 */

class ProductDetailFragment : BaseFragment() {

    companion object {
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
    }

    private fun setupToolbar(data: Product) {
        binding.toolbar.apply {
            title = data.name
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener { baseActivity.finish() }
        }
    }

    private fun onGetProductSuccess(data: Product) {
        binding.layout.root.visible()
        binding.collapsingToolbar.visible()
        binding.message.root.visible(false)
        imageLoader.loadWithUrl(data.imageUrl, binding.header.image)
        binding.header.name.text = data.name
        binding.header.price.text = data.getFormattedPrice(requireContext())
        binding.layout.description.text = data.description
    }

    private fun onGetProductError(error: Any?, shouldClose: Boolean = false) {
        binding.layout.root.visible(false)
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

    private fun onRetryButtonClick(view: View) {
        viewModel.getProductById(productId!!)
    }

    private fun onCloseButtonClick(view: View) {
        baseActivity.finish()
    }

}