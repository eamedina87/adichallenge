package tech.medina.adichallenge.ui.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.PagerSnapHelper
import tech.medina.adichallenge.databinding.FragmentItemDetailBinding
import tech.medina.adichallenge.databinding.FragmentItemListBinding
import tech.medina.adichallenge.domain.models.DataState
import tech.medina.adichallenge.domain.models.Product
import tech.medina.adichallenge.ui.common.BaseFragment
import tech.medina.adichallenge.ui.product.ProductViewModel
import tech.medina.adichallenge.ui.product.list.adapter.ProductListAdapter

class ProductListFragment : BaseFragment() {

    companion object {
        fun create(): ProductListFragment = ProductListFragment()
    }

    override val viewModel: ProductViewModel by viewModels()
    lateinit var binding: FragmentItemListBinding

    override fun getBindingRoot(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(savedInstanceState: Bundle?) {
        initObservers()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //todo save search text
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //todo get search text
    }


    private fun initObservers() {
        viewModel.products.observe(this, Observer {
            it?.let { products ->
                if (products.isEmpty()) {
                    onGetProductsEmpty()
                } else {
                    onGetProductsSuccess(products)
                }
            }
        })
        viewModel.loader.observe(this, Observer {
            it?.let { show ->
                if (show) showLoader() else hideLoader()
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let { error ->
                onGetProductsError(error)
            }
        })
    }

    private fun onGetProductsSuccess(list: List<Product>) {
        binding.itemList.apply {
            adapter = ProductListAdapter(imageLoader) { clickedProduct ->
                navigator.goToDetail(baseActivity, clickedProduct.id)
            }.apply {
                submitList(list)
            }
            if (this.onFlingListener == null) PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    private fun onGetProductsEmpty() {

    }

    private fun onGetProductsError(error: Any?) {

    }

    private fun showLoader() {

    }

    private fun hideLoader() {

    }
}